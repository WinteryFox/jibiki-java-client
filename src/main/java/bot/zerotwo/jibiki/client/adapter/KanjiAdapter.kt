package bot.zerotwo.jibiki.client.adapter

import bot.zerotwo.jibiki.client.io.ApiAdapter
import bot.zerotwo.jibiki.client.io.JibikiApiClient
import bot.zerotwo.jibiki.client.model.Kanji
import reactor.core.publisher.Flux
import java.net.URLEncoder

/**
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
class KanjiAdapter(apiClient: JibikiApiClient) : ApiAdapter(apiClient) {

    /**
     * Returns a Flux of Kanji from the Jibiki API for the provided query
     *
     * @param query The query param to send to the Jibiki API
     *
     * @return Flux of Kanji the result of the query
     */
    fun getKanji(query: String): Flux<Kanji> {
        return getFlux("/kanji?query=${URLEncoder.encode(query, charset)}", Kanji::class.java)
    }

}