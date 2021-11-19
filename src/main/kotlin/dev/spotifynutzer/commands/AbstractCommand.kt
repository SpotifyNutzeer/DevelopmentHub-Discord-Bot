package dev.spotifynutzer.commands

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData

abstract class AbstractCommand {

    abstract fun onCommand(member: Member, channel: TextChannel, event: SlashCommandEvent)

    abstract fun getCommand(): String

    abstract fun getDescription(): String

    open fun getCommandData(): CommandData = CommandData(getCommand(), getDescription())
}