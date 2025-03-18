package io.github.zzzyyylllty.chotenchat.data

import java.util.Date
import java.util.UUID

/**
 * 消息
 *
 * message含有：uuid，内容，sender，发送时间，提及的人，关注的联系人，发送到的联系人，类型。
 * */
data class Message(
    val uuid: UUID,
    val content: String, // 原聊天内容
    val sender: User,
    val sendTime: Date?,
    val mentionUser: List<String>, // KID
    val subscribedContact: String, // KID
    val sendGoalContact: String,
    val type: MessageType

    )

data class patchedMessage(
    val format: String, // 格式化聊天内容，只剩下{message}没有替换
    val message: Message,
    val receiveMode: ReceiveMode, // 根据接受模式生成消息
)
enum class MessageType {
    TEXT,
    PICTURE,
    SYSTEM
}