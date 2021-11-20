package dev.spotifynutzer.listener

import dev.spotifynutzer.models.EmbedModel
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class JoinListener : ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val channel: TextChannel? = event.guild.getTextChannelById(758685566933532734L)

        if (channel == null) return

        EmbedModel(
            "**Herzlich Willkommen**",
            event.member,
            null,
            Color.GREEN,
            "Der Nutzer " + event.member.getUser().getAsTag() + " hat den Server betreten! Viel Spaß!",
            event.member.user.getAvatarUrl(),
            event.guild
        ).createMessage().sendTextChannel(channel)
    }

}