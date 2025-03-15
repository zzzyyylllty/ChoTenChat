package io.github.zzzyyylllty.melsydchat.listener

import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {
    e.renderer(ChatRenderer { source: Player, sourceDisplayName: Component, message: Component, viewer: Audience ->
        sourceDisplayName
            .append(Component.text(": "))
            .append(message)
    })
    Bukkit.broadcastMessage("1")
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {

}

