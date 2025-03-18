package io.github.zzzyyylllty.chotenchat.function.internalMessage

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender

fun CommandSender.sendInternalMessages(message: String, asComponent: Boolean = true) {
    if (asComponent) this.sendStringAsComponent("<light_purple><italic>ChoTenChat -> <reset>$message")
    else  this.sendMessage("§d§oChoTenChat -> §r$message")
}


fun CommandSender.sendStringAsComponent(message: String) {
    val mm = MiniMessage.miniMessage()
    (this as Audience).sendMessage(mm.deserialize(message))
}

