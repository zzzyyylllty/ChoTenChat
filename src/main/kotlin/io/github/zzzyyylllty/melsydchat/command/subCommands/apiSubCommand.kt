package io.github.zzzyyylllty.melsydchat.command.subCommands

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand

val apiSubCommand = subCommand {
    // 参数 Content
    dynamic("content") {
        execute<CommandSender> { sender, context, argument ->
            val mm = MiniMessage.miniMessage()
            // 获取参数的值
            val content = context["content"]
            sender.sendMessage("&7Original: &r$content")
            (sender as Audience).sendMessage(mm.deserialize(content))
        }
    }
}
