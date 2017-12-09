package com.github.cnguy.OneTricks

import net.dv8tion.jda.core.AccountType.BOT
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.ChannelType
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.io.File

fun main(args: Array<String>) {
    val token = File(".token").readText()
    val jda: JDA = JDABuilder(BOT)
            .setToken(token)
            .addEventListener(ReadyListener())
            .addEventListener(MessageListener())
            .buildBlocking()
}

class ReadyListener: EventListener {
    override fun onEvent(event: Event) {
        if (event is ReadyEvent) {
            println("API is ready!")
        }
    }
}

class MessageListener: ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent?) {
        val messageContent = event!!.message.content
        if (event.isFromType(ChannelType.PRIVATE)) {
            val username = event.author.name
            println("[PM] $username: $messageContent")
        } else {
            val guildName = event.guild.name
            val textChannelName = event.textChannel.name
            val prefix = "[$guildName][$textChannelName]"
            val body = "${event.member.effectiveName}: $messageContent"
            println("$prefix $body")
        }
    }
}
