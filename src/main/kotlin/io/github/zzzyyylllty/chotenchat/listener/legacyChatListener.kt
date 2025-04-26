package io.github.zzzyyylllty.chotenchat.listener

import io.github.zzzyyylllty.chotenchat.data.ContainedContactType
import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createOrWipeUser
import io.github.zzzyyylllty.chotenchat.logger.warningL
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.ConsoleCommandSender
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asOrCreateUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.getMember
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.toUnContained
import io.github.zzzyyylllty.chotenchat.function.message.buildCompString
import io.github.zzzyyylllty.chotenchat.function.message.buildMessage
import io.github.zzzyyylllty.chotenchat.logger.severeS
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.asLangText
import java.time.LocalDateTime


@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {
    var sender = e.player.asUser()
    val mm = MiniMessage.miniMessage()
        if (sender == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", e.player.name, e.player.uniqueId)
            sender = e.player.asOrCreateUser()
        }
        val subscribe = sender.data.subscribeContact
        if (subscribe == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
            e.player.severeS(e.player.asLangText("CHAT_SEVERE_NO_SUBSCRIBE_FOUND"))
            return
        }
        val chotenMessage = Message(
            content = mm.serialize(e.message()),
            sender = sender,
            subscribeContact = sender.getSubscribeContact()!!,
            receiveContacts = listOf(subscribe.toUnContained()),
            uuid = e.player.uniqueId,
            mentionedUsers = emptyList(), // TODO
            sendTime = LocalDateTime.now(),
            reply = null
        )

    if (subscribe.contactType == ContainedContactType.GROUP) {
        val member = sender.getMember(subscribe.longId)?.temperature?.plus(1)
        if (member != null) sender.getMember(subscribe.longId)?.temperature = member
    }

    val compedString = chotenMessage.buildCompString()
        e.renderer { source: Player?, sourceDisplayName: Component?, message: Component?, viewer: Audience? ->
            if (viewer !is ConsoleCommandSender) {
                val receiver = (viewer as Player).asUser()
                if (receiver != null) {
                    mm.deserialize(chotenMessage.buildMessage(compedString, receiver))
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
    if (e.player.asUser() == null) e.player.createOrWipeUser()
    // createGroup(1000000, "ChoTenGroup", "ChoTenGroup")
}