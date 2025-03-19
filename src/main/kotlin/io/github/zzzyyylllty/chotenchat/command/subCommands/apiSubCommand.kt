package io.github.zzzyyylllty.chotenchat.command.subCommands

import io.github.zzzyyylllty.chotenchat.function.internalMessage.sendInternalMessages
import io.github.zzzyyylllty.chotenchat.function.kether.evalKether
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
@CommandHeader(
    name = "chotenchatapi",
    aliases = ["dylsemchatapi","ctca","chatapi"],
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

    /** Kether */
    @CommandBody
    val evalKether = subCommand {
        dynamic("script") {
            execute<CommandSender> { sender, context, argument ->
                val mm = MiniMessage.miniMessage()
                // 获取参数的值
                val content = context["script"]
                val ret = content.evalKether(sender as Player)
                sender.sendInternalMessages("Kether: $content", false)
                sender.sendInternalMessages("Return: ${ret.toString()}")
            }
        }
    }

    /** Kether */
    @CommandBody
    val evalKetherByPlayer = subCommand {
        player("player") {
            dynamic("script") {
                execute<CommandSender> { sender, context, argument ->
                    val tabooPlayer = context.player("player")
                    val bukkitPlayer = tabooPlayer.castSafely<Player>()
                    val mm = MiniMessage.miniMessage()
                    // 获取参数的值
                    val content = context["script"]
                    val ret = content.evalKether(bukkitPlayer as CommandSender)
                    sender.sendInternalMessages("Kether: $content", false)
                    sender.sendInternalMessages("Return: ${ret.toString()}")
                }
            }
        }
    }


}
