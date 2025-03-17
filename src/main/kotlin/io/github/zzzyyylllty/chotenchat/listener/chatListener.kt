package io.github.zzzyyylllty.chotenchat.listener

import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.MessageType
import io.github.zzzyyylllty.chotenchat.data.asUser
import io.github.zzzyyylllty.chotenchat.function.contact.createGroup
import io.github.zzzyyylllty.chotenchat.function.contact.createOrWipeUser
import io.github.zzzyyylllty.chotenchat.function.message.buildComponent
import io.github.zzzyyylllty.chotenchat.function.message.patch
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.warningL
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.craftbukkit.v1_20_R3.util.TerminalConsoleWriterThread
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import java.util.Calendar
import java.util.UUID


@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {
        val sender = e.player.asUser()
        val mm = MiniMessage.miniMessage()
        val mentionedUser = e.message() // TODO
        if (sender == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", e.player.name, e.player.uniqueId)
            throw NullPointerException()
        }
        val subscribe = sender.asUserData()?.subscribedContact
        if (subscribe == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
            throw NullPointerException()
        }
        val chotenMessage = Message(
            uuid = UUID.randomUUID(),
            content = mm.serialize(e.message()),
            sender = sender,
            sendTime = Calendar.getInstance().time,
            mentionUser = emptyList(),
            subscribedContact = subscribe,
            sendGoalContact = subscribe,
            type = MessageType.TEXT
        )
        e.renderer { source: Player?, sourceDisplayName: Component?, message: Component?, viewer: Audience? ->
            if (viewer !is ConsoleCommandSender) {
                val receiver = (viewer as Player).asUser()
                if (receiver != null) {
                    chotenMessage.patch(receiver).buildComponent()
                } else {
                    throw NullPointerException("receiver is null!")
                }
            } else {
                mm.deserialize("<light_purple>[ChoTenChat] <white>${source?.displayName}</white> <gray>${mm.serialize(message!!)} <dark_gray>($message)")
            }
        }
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create
    e.player.createOrWipeUser()
    createGroup("1000000","ChoTenGroup","ChoTenGroup")
}
