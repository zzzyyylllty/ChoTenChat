package io.github.zzzyyylllty.melsydchat.command.subCommands

import io.github.zzzyyylllty.melsydchat.function.internalMessage.sendInternalMessages
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.subCommand
@CommandHeader(
    name = "chotenchatapi",
    aliases = ["melsydchatapi","dylsemchatapi","ctca","chatapi"],
    permission = "chotenchat.command.api",
    description = "API Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatApiCommand {

    /** 解析 Minimessage */
    @CommandBody
    val minimessage = subCommand {
        dynamic("content") {
            execute<CommandSender> { sender, context, argument ->
                val mm = MiniMessage.miniMessage()
                // 获取参数的值
                val content = context["content"]
                sender.sendInternalMessages("Original: $content", false)
                sender.sendInternalMessages(content)
            }
        }
    }


}
