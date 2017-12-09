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
        if (event!!.isFromType(ChannelType.PRIVATE)) {
            println("[PM] ${event.author.name}: ${event.message.content}")
        } else {
            println(
                    "[${event.guild.name}][${event.textChannel?.name}]" +
                    " ${event.member.effectiveName}: ${event.message?.content}"
            )
        }
    }
}
