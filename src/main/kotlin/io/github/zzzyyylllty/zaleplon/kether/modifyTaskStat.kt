package io.github.zzzyyylllty.zaleplon.kether

import io.github.zzzyyylllty.tasks.debugger.maniuallyCompleteTask
import io.github.zzzyyylllty.zaleplon.Zaleplon.questsMap
import io.github.zzzyyylllty.zaleplon.data.QuestStat
import org.bukkit.entity.Player
import taboolib.common.util.isPlayer
import taboolib.module.kether.*


@KetherParser(["kquest"], shared = true)
fun parser1() = scriptParser {
    it.switch {
        case("active", "unlock", "accept") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer()) questsMap[sender as Player]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            }
        }
        case("fail") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "fail",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    false
                )
            }
        }
        case("complete", "done", "finish") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "complete",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    false
                )
            }
        }
        case("restart", "reaccept") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "restart",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    true
                )
            }
        }

        case("active-silent", "unlock-silent", "accept-silent") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer()) questsMap[sender as Player]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            }
        }
        case("fail-silent") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "fail",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    true
                )
            }
        }
        case("complete-silent", "done-silent", "finish-silent") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "complete",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    true
                )
            }
        }

        case("restart-silent", "reaccept-silent") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "fail",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender,
                    true
                )
            }
        }


    }
}