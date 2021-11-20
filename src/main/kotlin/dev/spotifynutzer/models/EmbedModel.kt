package dev.spotifynutzer.models

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import java.awt.Color
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.TemporalAccessor
import java.util.concurrent.TimeUnit

class EmbedModel {

    private var author: Member? = null
    private var title: String? = null
    private var image: String? = null
    private var thumbnail: String? = null
    private var footer: String? = null
    private var color: Color? = null
    private var text: String? = null
    private var guild: Guild? = null
    private var embedBuilder: EmbedBuilder? = null
    private var timestep: TemporalAccessor? = null


    constructor(
        title: String?,
        author: Member?,
        image: String?,
        color: Color?,
        text: String?,
        thumbnail: String?,
        guild: Guild,
    ) {
        this.author = author
        this.title = title
        this.image = image
        this.color = color
        this.text = text
        this.thumbnail = thumbnail
        this.guild = guild
        timestep = OffsetDateTime.now()
        footer = guild.name
        embedBuilder = EmbedBuilder()
    }

    fun createMessage(): EmbedModel {
        embedBuilder!!.setAuthor("» ${author?.effectiveName}", "https://spotifynutzer.xyz/", author?.user?.avatarUrl)
        embedBuilder!!.setImage(image)
        embedBuilder!!.setColor(color)
        embedBuilder!!.setFooter(footer, guild?.iconUrl)
        embedBuilder!!.setThumbnail(thumbnail)
        embedBuilder!!.setTimestamp(timestep)
        if (!text.isNullOrBlank() and !title.isNullOrBlank()) {
            embedBuilder!!.addField(title, text, false)
        }
        return this
    }

    fun addField(name: String?, value: String?, inline: Boolean): EmbedModel {
        embedBuilder!!.addField(name, value, inline)
        return this
    }

    fun sendPrivate(privateChannel: PrivateChannel) {
        privateChannel.sendMessage(embedBuilder!!.build()).queue()
    }

    fun sendTextChannel(channel: TextChannel) {
        channel.sendMessage(embedBuilder!!.build()).queue()
    }

    fun sendTextChannelAndDeleteLater(channel: TextChannel, delay: Long) {
        channel.sendMessage(embedBuilder!!.build()).delay(Duration.ofSeconds(delay)).flatMap(Message::delete).queue()
    }

    fun sendTextChannel(embedBuilder: EmbedBuilder, textChannel: TextChannel) {
        textChannel.sendMessage(embedBuilder.build()).queue()
    }

    fun reply(message: Message) {
        message.reply(embedBuilder!!.build()).mentionRepliedUser(false).queue()
    }

    fun editMessage(message: Message) {
        message.editMessage(" ")
        message.editMessage(embedBuilder!!.build()).queue()
    }

    fun getEmbed(): MessageEmbed {
        return embedBuilder!!.build()
    }

    fun deleteDelayed(time: Int, channel: TextChannel) {
        channel.sendMessage(embedBuilder!!.build())
            .delay(time.toLong(), TimeUnit.SECONDS)
            .flatMap { obj: Message -> obj.delete() }.queue()
    }

    fun replyToSlashCommand(event: SlashCommandEvent) {
        val message = event.channel.sendMessage(embedBuilder!!.build()).complete()
        event.reply(message).queue()
    }

}