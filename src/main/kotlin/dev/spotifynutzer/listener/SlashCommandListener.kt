package dev.spotifynutzer.listener

import dev.spotifynutzer.Bot
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommandListener : ListenerAdapter() {

    override fun onSlashCommand(event: SlashCommandEvent) {
        Bot.getInstance().getCommandManager().onCommand(event)
    }
}