package io.github.zzzyyylllty.chotenchat.function.kether.script

import net.kyori.adventure.audience.Audience
import taboolib.module.kether.KetherParser
import taboolib.module.kether.actionNow
import taboolib.module.kether.script
import taboolib.module.kether.scriptParser
import taboolib.module.kether.switch
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.module.kether.combinationParser


@KetherParser(["repl-legacy","replacelegacy","componenttext","comptext"], shared = true)
fun actionReplaceLegacyy() = combinationParser {
    val mm = MiniMessage.miniMessage()
    it.group(text()).apply(it) { str ->
        now {
            val mm = MiniMessage.miniMessage()
            val deserialized = LegacyComponentSerializer.builder().build().deserialize(str)
            return@now mm.serialize(deserialized)
        }
    }
}
/*scriptParser {
    it.switch {
        case("minitell","minimessage") {
            val message = it.nextParsedAction().
            val mm = MiniMessage.miniMessage()
            actionNow {
                val sender = script().sender?.castSafely<CommandSender>()
                (sender as Audience).sendMessage(mm.deserialize(message))
            }
        }
    }
}*/