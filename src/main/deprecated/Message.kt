package io.github.zzzyyylllty.melsydchat.data

import io.github.zzzyyylllty.melsydchat.function.asUserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
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
) {
    fun isMentioned(user: User): Boolean {
        if (
            content.contains("@${user.name}") ||
            content.contains("@${Bukkit.getPlayer(user.playerUUID)?.name}") ||
            content.contains("@all") ||
            content.contains("@g${user.asUserData()?.subscribeContact?.id}")
            ) {
            return true
        } else return false
    }
}

enum class MessageType {
    SYSTEM,
    TEXT,
    IMAGE,
    LINK,
    ANNOUNCEMENT
}

data class buildedMessage(
    val original: Message,
    val builded: Component,
)