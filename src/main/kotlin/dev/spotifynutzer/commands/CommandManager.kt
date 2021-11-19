package dev.spotifynutzer.commands

import dev.spotifynutzer.Bot
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent

class CommandManager {

    /**     List with all commands  **/
    private val commands: ArrayList<AbstractCommand> = ArrayList()

    init {

        /**     Updating commands   **/
        val commandListUpdateAction = Bot.getInstance().getBotBuilder().getJDA().updateCommands()

        commands.forEach {
            commandListUpdateAction.addCommands(it.getCommandData())
        }

        commandListUpdateAction.queue()
    }

    /**     Executing a command     **/
    fun onCommand(event: SlashCommandEvent) {

        event.deferReply().queue()

        /**     Finding the command     **/
        val command: List<AbstractCommand>? = commands.filter {
            it.getCommand() == event.name
        }

        /**     Command couldn´t be found   **/
        if (command.isNullOrEmpty()) event.reply("Dieser Befehl konnte nicht gefunden werden. Bitte melde dies SpotifyNutzer | Paul#1234.").queue()

        /**     Executing the command   **/
        command!!.first().onCommand(event.member!!, event.textChannel, event)

    }

    fun addCommand(vararg command: AbstractCommand) {

        if (!commands.containsAll(command.toList())) commands.addAll(command)

    }

}