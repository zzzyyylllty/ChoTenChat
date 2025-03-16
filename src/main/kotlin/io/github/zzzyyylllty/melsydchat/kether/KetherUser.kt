package io.github.zzzyyylllty.melsydchat.kether

import ink.ptms.adyeshach.taboolib.module.kether.ParserHolder.action
import io.github.zzzyyylllty.melsydchat.data.User
import org.bukkit.Bukkit
import taboolib.library.kether.ArgTypes
import taboolib.library.kether.ParsedAction
import taboolib.module.kether.KetherParser
import taboolib.module.kether.ScriptAction
import taboolib.module.kether.ScriptFrame
import taboolib.module.kether.actionNow
import taboolib.module.kether.combinationParser
import taboolib.module.kether.scriptParser
import taboolib.module.kether.switch
import java.util.concurrent.CompletableFuture


/**
 * User
 *
 * 获取用户实例。
 *
 * Usage:
 * ** user (player|id) {token}**
 * ```
 * user player "AmeChan"
 * user id 20000505
 * ```
 *
 * */
@KetherParser(["user"],namespace = "melsyd",shared = true)
fun parser() = scriptParser {
    it.switch {
        case("id") {
            UserIdKether(it.nextParsedAction())
        }
        case("name") {
            UserIdKether(it.nextParsedAction())
        }
    }
}

class UserIdKether(val value: ParsedAction<*>): ScriptAction<User?>() {
    override fun run(frame: ScriptFrame): CompletableFuture<User?> {
        val id = value
        return CompletableFuture.completedFuture(null)
    }
}

class UserNameKether(val value: ParsedAction<*>): ScriptAction<User?>() {
    override fun run(frame: ScriptFrame): CompletableFuture<User?> {
        val id = Bukkit.getPlayer(frame.name())
        return CompletableFuture.completedFuture(null)
    }
}
