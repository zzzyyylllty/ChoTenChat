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
import io.github.zzzyyylllty.chotenchat.function.message.call
import io.github.zzzyyylllty.chotenchat.logger.severeS
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.configuration.ConfigNode
import taboolib.platform.util.asLangText
import java.time.LocalDateTime


@ConfigNode("chat.cancel-event", "settings.yml")
var cancelEvent = false

@SubscribeEvent(priority = EventPriority.HIGHEST, ignoreCancelled = true)
fun onBukkitChatEvent(e: AsyncPlayerChatEvent) {
    if (e.isCancelled) return
    if (cancelEvent) {
        e.isCancelled = true
    } else {
        e.recipients.clear()
    }
    val sender = e.player.asUser() ?: throw NullPointerException("User for event player ${e.player} not found")
    val subscribe = sender.data.subscribeContact
    if (subscribe == null) {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
        e.player.severeS(e.player.asLangText("CHAT_SEVERE_NO_SUBSCRIBE_FOUND"))
        return
    }
    val chotenMessage = Message(
        content = e.message,
        sender = sender,
        subscribeContact = sender.getSubscribeContact()!!,
        receiveContacts = listOf(subscribe.toUnContained()),
        uuid = e.player.uniqueId,
        mentionedUsers = emptyList(), // TODO
        sendTime = LocalDateTime.now(),
        reply = null
    )
    chotenMessage.call()
}