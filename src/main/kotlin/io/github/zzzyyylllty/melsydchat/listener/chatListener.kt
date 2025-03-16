package io.github.zzzyyylllty.melsydchat.listener

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageType
import io.github.zzzyyylllty.melsydchat.function.asUser
import io.github.zzzyyylllty.melsydchat.function.asUserData
import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
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
        val user = e.player.asUser()?: run {
            error(console.asLangText("INTERNAL_USER_NOT_FOUND", e.player.name, e.player.uniqueId))
            return@submitAsync }
        val goal = user.asUserData()?.subscribeContact ?: run {
            error(console.asLangText("INTERNAL_SUBSCRIBE_CONTACT_NOT_FOUND", e.player.name))
            return@submitAsync }
        val message = Message(
            uuid = UUID.randomUUID(),
            goal = goal,
            type = MessageType.TEXT,
            sender = user,
            content = e.message,
            formatted = TODO(),
            sendedTime = Calendar.getInstance(),
            meta = LinkedHashMap<String, String>()
        )
    }
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create

}