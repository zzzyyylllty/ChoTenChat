package io.github.zzzyyylllty.zaleplon.functions

import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptCommandSender
import taboolib.module.kether.KetherShell
import taboolib.module.kether.ScriptOptions
import java.util.concurrent.CompletableFuture

fun runKether(script: List<String>, player: Player): CompletableFuture<Any> {
    return KetherShell.eval(
        script, options = ScriptOptions(
            sender = adaptCommandSender(player)
        )
    ).thenApply { it }
}
