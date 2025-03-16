package io.github.zzzyyylllty.melsydchat.data

import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import net.kyori.adventure.text.Component
import org.bukkit.event.Event
import java.util.Calendar
import java.util.UUID
import kotlin.math.floor

data class Message (
    val uuid: UUID,
    val sender: User,
    val content: String, // 原内容
    val formatted: Component, // 处理后内容
    val sendedTime: Calendar,
    val meta: LinkedHashMap<String, String>,
)