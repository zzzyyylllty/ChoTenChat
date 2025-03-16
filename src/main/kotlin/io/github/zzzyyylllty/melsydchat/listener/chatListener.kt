package io.github.zzzyyylllty.melsydchat.listener

import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.OtherAccount
import io.github.zzzyyylllty.melsydchat.data.ProfileCard
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.data.selectableContact
import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import java.util.UUID

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
    // create

}