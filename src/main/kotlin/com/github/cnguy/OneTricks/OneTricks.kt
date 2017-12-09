package com.github.cnguy.OneTricks

import net.dv8tion.jda.core.AccountType.BOT
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.hooks.EventListener
import java.io.File

fun main(args: Array<String>) {
    val token = File(".token").readText()
    val jda: JDA = JDABuilder(BOT)
            .setToken(token)
            .addEventListener(ReadyListener())
            .buildBlocking()
}

class ReadyListener: EventListener {
    override fun onEvent(event: Event) {
        if (event is ReadyEvent) {
            println("API is ready!")
        }
    }
}
