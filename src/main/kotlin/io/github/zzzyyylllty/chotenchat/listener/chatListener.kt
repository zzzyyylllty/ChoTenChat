package io.github.zzzyyylllty.chotenchat.listener

import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createOrWipeUser
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.warningL
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.craftbukkit.v1_20_R3.util.TerminalConsoleWriterThread
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUserOrFail
import io.github.zzzyyylllty.chotenchat.function.internalMessage.sendInternalMessages
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.asLangText
import java.util.Calendar
import java.util.UUID


@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {/*
        if (sender == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", e.player.name, e.player.uniqueId)
            throw NullPointerException()
        }
        val subscribe = sender.data.subscribeContact
        if (subscribe == null) {
            warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
            e.player.sendInternalMessages(e.player.asLangText("CHAT_SEVERE_NO_SUBSCRIBE_FOUND"))
            return
        }
        val chotenMessage = Message(
            content = mm.serialize(e.message()),
            sender = sender,
            subscribeContact = subscribe,
            receiveContacts = listOf(subscribe),
            uuid = e.player.uniqueId,
            mentionedUsers = emptyList(), // TODO
            sendTime = Calendar.getInstance(),
            reply = null
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
        }*/
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create
    e.player.createOrWipeUser()
    // createGroup(1000000, "ChoTenGroup", "ChoTenGroup")
    e.player.asUser()
}