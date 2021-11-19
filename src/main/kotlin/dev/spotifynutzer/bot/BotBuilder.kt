package dev.spotifynutzer.bot

import dev.spotifynutzer.listener.SlashCommandListener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.requests.GatewayIntent
import javax.security.auth.login.LoginException

class BotBuilder(private val token: String) {

    private lateinit var jdaBuilder: JDABuilder
    private lateinit var jda: JDA
    private lateinit var user: User

    @Throws(LoginException::class)
    fun build() {
        jdaBuilder = JDABuilder.createDefault(token)
            .enableIntents(
                GatewayIntent.GUILD_MEMBERS
            )
            .addEventListeners(
                SlashCommandListener()
            )

        jdaBuilder.setActivity(Activity.playing("nix"))

        jda = jdaBuilder.build()
        user = jda.selfUser
    }

    fun getJDA(): JDA = jda
    fun getUser(): User = user

}