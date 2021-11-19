package dev.spotifynutzer

import dev.spotifynutzer.bot.BotBuilder
import dev.spotifynutzer.commands.CommandManager

class Bot(token: String) {

    /**     Final values    **/
    private val token = token
    private val botBuilder = BotBuilder(token)
    private val commandManager: CommandManager

    init {
        /**     Setting the instance    **/
        instance = this

        /**     Instantiating CommandManager    **/
        commandManager = CommandManager()

        /**     Starting the bot here   **/
        botBuilder.build()
    }

    /**     Getter      **/
    fun getBotBuilder(): BotBuilder = botBuilder

    fun getCommandManager(): CommandManager = commandManager

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