package io.github.zzzyyylllty.melsydchat.data

import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import net.kyori.adventure.text.Component
import org.bukkit.event.Event
import java.util.Calendar
import java.util.UUID
import kotlin.math.floor

data class Message (
    val uuid: UUID,
    val goal: Contact?,
    val type: MessageType,
    val sender: User,
    val content: String, // 原内容
    val sendedTime: Calendar,
    val meta: LinkedHashMap<String, String>,
)

enum class MessageType {
    SYSTEM,
    TEXT,
    IMAGE,
    LINK,
    ANNOUNCEMENT
}

data class PatchedMessage(
    val original: Message,
    val patched: Component,
)