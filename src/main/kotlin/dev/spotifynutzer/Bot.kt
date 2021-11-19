package dev.spotifynutzer

import dev.spotifynutzer.bot.BotBuilder

class Bot(token: String) {

    /**     Final values    **/
    private val token = token
    private val botBuilder = BotBuilder(token)

    init {
        /**     Setting the instance    **/
        instance = this

        /**     Starting the bot here   **/
        botBuilder.build()
    }

    fun getBotBuilder(): BotBuilder = botBuilder

    /**     Instance    **/
    companion object {
        private lateinit var instance: Bot

        @JvmStatic
        fun getInstance(): Bot = instance
    }


}

fun main() {

    Bot("TOKEN")

}