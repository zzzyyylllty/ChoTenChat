package io.github.zzzyyylllty.chotenchat.data

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.Calendar
import java.util.UUID

@Serializable
public data class Message(
    val content: String,
    val sender: User,
    val subscribeContact: Contact,
    val receiveContacts: List<Contact>,
    val uuid: UUID,
    val mentionedUsers: List<User>,
    val sendTime: LocalDateTime,
    val reply: Message?
)