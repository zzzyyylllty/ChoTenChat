package io.github.zzzyyylllty.chotenchat.function.internalMessage

import io.github.zzzyyylllty.chotenchat.logger.fineS
import io.github.zzzyyylllty.chotenchat.logger.sendStringAsComponent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import taboolib.common.platform.function.warning

@Deprecated(
    "Deprecated. use CommandSender.infoS instead.",
    ReplaceWith("langLog"),
    DeprecationLevel.ERROR
)
fun CommandSender.sendInternalMessages(message: String) {
    if (this is ConsoleCommandSender) fineS(message)
    else this.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#ccccff>MESSA</#ccccff>]</gray> <reset>$message")
}

