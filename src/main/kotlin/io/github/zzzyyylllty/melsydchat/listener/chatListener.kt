package io.github.zzzyyylllty.melsydchat.listener

import io.github.zzzyyylllty.melsydchat.data.
import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.asUser
import io.github.zzzyyylllty.melsydchat.function.message.messageFromEvent
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync
import taboolib.module.lang.asLangText
import java.util.Calendar
import java.util.TimeZone
import java.util.UUID

@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncPlayerChatEvent) {
    submitAsync {
        messageFromEvent(e)
    }
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create

}