package bot.zerotwo.jibiki.client.io

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.handler.codec.http.HttpMethod
import org.json.JSONArray
import org.json.JSONObject
import org.reactivestreams.Subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.nio.charset.Charset

/**
 * Class for inheritance by api adapters for the JibikiApiClient
 *
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
open class ApiAdapter(private val apiClient: JibikiApiClient) {

    /**
     * Default charset for encoding queries and responses
     */
    protected val charset: Charset = Charset.forName("UTF-8")

    /**
     * Method to call the Jibiki API
     *
     * @param uri path for the request, must not include the base uri and must start with a leading slash
     * @param method HttpMethod to use for this request, default HttpMethod.GET
     */
    protected fun callApi(uri: String, method: HttpMethod = HttpMethod.GET): HttpClient.RequestSender {
        return apiClient.httpClient
                .request(method)
                .uri(uri)
    }

    /**
     * Makes a get call to the Jibiki API.
     * This method assumes that the response is in JSON format and is initially a JSONArray with it child objects
     * being JSONObject from POJO of class `class`
     *
     * @param uri the uri to use for this request, @see callApi
     * @param `class` the POJO class of the nested objects in the JSONArray
     */
    protected fun <T> getFlux(uri: String, `class`: Class<T>): Flux<T> {
        return callApi(uri)
                .responseSingle { response, bytes ->
                    val statusCode = response.status().code()
                    if (statusCode != 200)
                        throw RuntimeException("API returned invalid status code $statusCode")
                    bytes.asString(charset)
                }
                .filter(String::isNotEmpty)
                .switchIfEmpty(Mono.error(RuntimeException("Received empty response from API")))
                .map { JSONArray(it) }
                .flatMapMany { array ->
                    Flux.from { sub: Subscriber<in Any> ->
                        for (index in 0 until array.length()) {
                            sub.onNext(array[index])
                        }
                        sub.onComplete()
                    }
                }
                .map { it as JSONObject }
                .map { getMapper().readValue(it.toString(), `class`) }
    }

    /**
     * Jackson Databind Object Mapper
     *
     * @return object mapper to use for all POJOs
     */
    protected fun getMapper(): ObjectMapper {
        return MAPPER
    }

    companion object {
        private val MAPPER = ObjectMapper()
    }
}