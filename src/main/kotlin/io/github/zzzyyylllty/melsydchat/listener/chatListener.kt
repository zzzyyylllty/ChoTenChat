package io.github.zzzyyylllty.melsydchat.listener

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageType
import io.github.zzzyyylllty.melsydchat.data.asUser
import io.github.zzzyyylllty.melsydchat.logger.warningL
import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync
import java.util.Calendar
import java.util.UUID


@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {
    submitAsync {
        val sender = e.player.asUser()
        val mm = MiniMessage.miniMessage()
        val mentionedUser = e.message()
        if (sender == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", e.player.name, e.player.uniqueId)
            throw NullPointerException()
        }
        val subscribe = sender.asUserData()?.subscribedContact
        if (subscribe == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
            throw NullPointerException()
        }
        val message = Message(
            uuid = UUID.randomUUID(),
            content = mm.serialize(e.message()),
            sender = sender,
            sendTime = Calendar.getInstance().time,
            mentionUser = emptyList(),
            subscribedContact = subscribe,
            sendGoalContact = subscribe,
            type = MessageType.TEXT
        )
        e.renderer(ChatRenderer { source: Player?, sourceDisplayName: Component?, message: Component?, viewer: Audience? ->
            message.patch()
            return@ChatRenderer
        })
    }
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create

}
