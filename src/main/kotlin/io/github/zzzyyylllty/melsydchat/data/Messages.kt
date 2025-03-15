package io.github.zzzyyylllty.melsydchat.data

import java.util.Calendar
import java.util.UUID

data class Message (

    /** 消息的唯一 ID */
    val uuid: UUID?,

    val sendGoal: List<Contact>?,
    val type: MessageType?,
    val meta: LinkedHashMap<String, String>?,
    val content: String?,
    val sendedTime: Calendar?,

    /** 回复消息的唯一 ID */
    val reply: UUID?,
)

enum class MessageType {
    TEXT,
    IMAGE,
    REDPACKET,
    SHARE,
    ANNOUNCEMENT,
    SYSTEM,
    LINK,
    SHOUT,
    STACKED, // 叠加聊天记录, TODO
}

data class EssenceMessage (
    val message: Message,
    val adder: User,
    val addedTime: Calendar,
)

data class BuildedMessage (
    val message: Message,
    val adder: User,
    val addedTime: Calendar,
)
