package bot.zerotwo.jibiki.client.adapter

import bot.zerotwo.jibiki.client.io.ApiAdapter
import bot.zerotwo.jibiki.client.io.JibikiApiClient
import bot.zerotwo.jibiki.client.model.SentenceBundle
import reactor.core.publisher.Flux
import java.net.URLEncoder

/**
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
class SentencesAdapter(apiClient: JibikiApiClient) : ApiAdapter(apiClient) {

    /**
     * Returns a Flux of SentenceBundle from the Jibiki API for the provided query
     *
     * @param query The query param to send to the Jibiki API
     *
     * @return Flux of SentenceBundle the result of the query
     */
    fun getSentences(query: String): Flux<SentenceBundle> {
        return getFlux("/kanji?query=${URLEncoder.encode(query, charset)}", SentenceBundle::class.java)
    }

}