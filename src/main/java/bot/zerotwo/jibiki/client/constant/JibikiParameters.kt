package bot.zerotwo.jibiki.client.constant

/**
 * @author ByteAlex <bytealex@zerotwo.bot>
 */
object JibikiParameters {

    private fun env(name: String, default: String): String {
        return System.getenv(name) ?: default
    }

    /**
     * Base url of the Jibiki API
     */
    val API_BASE_URL: String = env("JIBIKI_API_BASE_URL", "https://api.jibiki.app")
}