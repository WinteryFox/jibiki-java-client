package bot.zerotwo.jibiki.client.io

import bot.zerotwo.jibiki.client.constant.JibikiParameters
import reactor.netty.http.client.HttpClient

/**
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
class JibikiApiClient {

    private val adapters: MutableMap<Class<out ApiAdapter>, ApiAdapter> = mutableMapOf()
    internal val httpClient: HttpClient = HttpClient.create()
            .baseUrl(JibikiParameters.API_BASE_URL)

    /**
     * Creates an instance of the api adapter, requires the class to have a constructor with
     * a single argument of type JibikiApiClient
     *
     * @param `class` the class of the ApiAdapter
     *
     * @return the new instance of the ApiAdapter
     */
    private fun <T : ApiAdapter> createAdapter(`class`: Class<T>): T {
        val constructor = `class`.getDeclaredConstructor(JibikiApiClient::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(this)
    }

    /**
     * Gets and casts an ApiAdapter from the adapters map to an ApiAdapter of type class
     *
     * @param `class` Class of the ApiAdapter
     *
     * @return cached instance of the ApiAdapter
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ApiAdapter> getAdapter(`class`: Class<T>): T {
        return adapters.computeIfAbsent(`class`) { createAdapter(it) } as T
    }
}