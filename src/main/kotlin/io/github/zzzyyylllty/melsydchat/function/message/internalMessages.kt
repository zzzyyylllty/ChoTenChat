package io.github.zzzyyylllty.melsydchat.function.message

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import taboolib.platform.util.asLangText

fun Player.internalMessages(message: String) {
    this.sendStringAsComponent("<light_purple><bold>ChoTenChat >> <yellow>$message")
}


fun Player.sendStringAsComponent(message: String) {
    val mm = MiniMessage.miniMessage()
    (this as Audience).sendMessage(mm.deserialize(message))
}

