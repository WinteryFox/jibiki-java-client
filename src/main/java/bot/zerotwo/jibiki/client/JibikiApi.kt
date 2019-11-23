package bot.zerotwo.jibiki.client

import bot.zerotwo.jibiki.client.io.ApiAdapter
import bot.zerotwo.jibiki.client.io.JibikiApiClient

/**
 * Main accessor class for the Jibiki API
 *
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
class JibikiApi private constructor() {

    private val client: JibikiApiClient = JibikiApiClient()

    /**
     * Gets a specific api adapter
     * @see bot.zerotwo.jibiki.client.adapter
     *
     * @param `class` Adapter-class to retrieve
     * @return instance of the adapter
     */
    fun <T : ApiAdapter> getAdapter(`class`: Class<T>): T {
        return client.getAdapter(`class`)
    }

    companion object {
        private var apiInstance: JibikiApi = JibikiApi()

        /**
         * Returns the cached instance of the JibikiApi
         * @return cached instance of JibikiApi
         */
        fun getJibikiApi(): JibikiApi {
            return apiInstance
        }
    }
}