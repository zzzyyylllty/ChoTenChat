package io.github.zzzyyylllty.melsydchat.data

import net.kyori.adventure.text.Component
import java.util.Calendar
import java.util.UUID

data class Message (

    /** 消息的唯一 ID */
    val uuid: UUID?,

    val sender: User,
    val subscribeContact: Contact,
    val type: MessageType,
    val meta: LinkedHashMap<String, String>,
    val content: Component,
    val sendedTime: Calendar,

    /** 回复消息 */
    val reply: Message?,
)

data class NullableSectionsMessage (

    /** 消息的唯一 ID */
    val uuid: UUID?,

    val sender: User,

    val sendGoal: List<Contact>?,
    val type: MessageType?,
    val meta: LinkedHashMap<String, String>?,
    val content: Component,
    val sendedTime: Calendar?,

    /** 回复消息 */
    val reply: Message?,
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
