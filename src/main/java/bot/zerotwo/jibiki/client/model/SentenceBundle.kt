package bot.zerotwo.jibiki.client.model

data class SentenceBundle(
        val id: Int = 0,
        val language: String = "",
        val sentence: String = "",
        val translations: List<Sentence> = mutableListOf()
)