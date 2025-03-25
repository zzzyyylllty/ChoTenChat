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
    name = "chotenchatai",
    aliases = ["dylsemchatai","ctcai","chatai"],
    permission = "chotenchat.command.ai",
    description = "AI Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatAiCommand {

    @CommandBody
    val chat = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.sendInternalMessages("In developing...", false)
        }
    }

    @CommandBody
    val name = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.sendInternalMessages("In developing...", false)
        }
    }


}
