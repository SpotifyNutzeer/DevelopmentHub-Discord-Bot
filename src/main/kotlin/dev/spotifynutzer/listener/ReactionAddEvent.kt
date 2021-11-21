package dev.spotifynutzer.listener

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ReactionAddEvent : ListenerAdapter() {

    override fun onMessageReactionAdd(event: MessageReactionAddEvent) {
        if (event.channel.idLong != 758720321678868502L) return
        if (event.reaction.reactionEmote.idLong != 911951309484199956L) return

        val role = event.guild.getRoleById(758692232798404638L)

        if (event.member!!.roles.contains(role)) return

        if (role != null) {
            event.guild.addRoleToMember(event.member!!, role)
        }
    }
}