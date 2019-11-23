package bot.zerotwo.jibiki.client.adapter

import bot.zerotwo.jibiki.client.io.ApiAdapter
import bot.zerotwo.jibiki.client.io.JibikiApiClient
import bot.zerotwo.jibiki.client.model.Word
import reactor.core.publisher.Flux
import java.net.URLEncoder

/**
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
class WordsAdapter(apiClient: JibikiApiClient) : ApiAdapter(apiClient) {

    /**
     * Returns a Flux of Word from the Jibiki API for the provided query
     *
     * @param query The query param to send to the Jibiki API
     *
     * @return Flux of Word the result of the query
     */
    fun getWords(query: String): Flux<Word> {
        return getFlux("/kanji?query=${URLEncoder.encode(query, charset)}", Word::class.java)
    }

}