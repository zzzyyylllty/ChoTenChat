package io.github.zzzyyylllty.chotenchat.logger

import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.consoleSender
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.common.platform.function.warning
import taboolib.module.lang.asLangText


fun infoL(node: String,vararg args: Any) {
    consoleSender?.infoS(console.asLangText(node,args))
}
fun severeL(node: String,vararg args: Any) {
    consoleSender?.severeS(console.asLangText(node,args))
}
fun warningL(node: String,vararg args: Any) {
    consoleSender?.warningS(console.asLangText(node,args))
}

fun CommandSender?.fineS(message: String) {
    (this ?:consoleSender)?.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#66ffcc>FINES</#66ffcc>]</gray> <reset>$message")
}

fun CommandSender?.debugS(message: String) {
    (this ?:consoleSender)?.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#ddaa77>DEBUG</#ddaa77>]</gray> <#aaaaaa>$message")
}

fun CommandSender?.infoS(message: String) {
    (this ?:consoleSender)?.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#66ccff>INFOS</#66ccff>]</gray> <reset>$message")
}

fun CommandSender?.warningS(message: String) {
    (this ?:consoleSender)?.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#ffee66>WARNI</#ffee66>]</gray> <#eeeeaa>$message")
}

fun CommandSender?.severeS(message: String) {
    (this ?:consoleSender)?.sendStringAsComponent("<gray>[<gradient:#99ccff:#dd66ff:#ff66aa>ChoTenChat</gradient>] [<#ff6600>ERROR</#ff6600>]</gray> <#ffccbb>$message")
}

fun CommandSender.sendStringAsComponent(message: String) {
    val mm = MiniMessage.miniMessage()
    val legacy = LegacyComponentSerializer.legacyAmpersand()
    (this as Audience).sendMessage(mm.deserialize(legacy.serialize(legacy.deserialize(message.replace("§", "&")))))
}
