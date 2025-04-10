package io.github.zzzyyylllty.chotenchat.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
public data class Message(
    val content: String,
    val sender: User,
    val subscribeContact: Contact,
    val receiveContacts: List<Contact?>,
    val uuid: @Contextual UUID,
    val mentionedUsers: List<User>,
    val sendTime: @Contextual LocalDateTime,
    val reply: Message?
)