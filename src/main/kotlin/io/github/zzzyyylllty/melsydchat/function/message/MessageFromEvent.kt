package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageType
import io.github.zzzyyylllty.melsydchat.data.asUser
import io.github.zzzyyylllty.melsydchat.logger.warningL
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.Calendar
import java.util.UUID

fun messageFromEvent(e: AsyncPlayerChatEvent): Message {
    val sender = e.player.asUser()
    val mentionedUser = e.message
    if (sender == null) {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", e.player.name, e.player.uniqueId)
        throw NullPointerException()
    }
    val subscribe = sender.asUserData()?.subscribedContact
    if (subscribe == null) {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_SUBSCRIBE", sender.registryName)
        throw NullPointerException()
    }
    return Message(
        uuid = UUID.randomUUID(),
        content = e.message,
        sender = sender,
        sendTime = Calendar.getInstance().time,
        mentionUser = emptyList(),
        subscribedContact = subscribe,
        sendGoalContact = subscribe,
        type = MessageType.TEXT
    )
}
