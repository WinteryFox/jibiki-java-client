package bot.zerotwo.jibiki.client.model

import bot.zerotwo.jibiki.client.model.Form
import bot.zerotwo.jibiki.client.model.Sense

data class Word(
        val id: Int = 0,
        val forms: Array<Form> = emptyArray(),
        val senses: Array<Sense> = emptyArray()
)