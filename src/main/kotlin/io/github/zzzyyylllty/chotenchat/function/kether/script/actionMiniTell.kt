package io.github.zzzyyylllty.chotenchat.function.kether.script

import net.kyori.adventure.audience.Audience
import taboolib.module.kether.KetherParser
import taboolib.module.kether.actionNow
import taboolib.module.kether.script
import taboolib.module.kether.scriptParser
import taboolib.module.kether.switch
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender


@KetherParser(["chotenchat", "cchat", "chat"], shared = true)
fun actionMiniTell() = scriptParser {
    it.switch {
        case("minitell","minimessage") {
            val message = it.nextToken()
            val mm = MiniMessage.miniMessage()
            actionNow {
                val sender = script().sender as CommandSender
                (sender as Audience).sendMessage(mm.deserialize(message))
            }
        }
    }
}