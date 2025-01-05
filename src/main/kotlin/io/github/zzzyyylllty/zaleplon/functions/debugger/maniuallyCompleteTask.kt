package io.github.zzzyyylllty.tasks.debugger

import io.github.zzzyyylllty.zaleplon.Zaleplon.questsMap
import io.github.zzzyyylllty.zaleplon.data.QuestStat
import io.github.zzzyyylllty.zaleplon.functions.runKether
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.util.asList

fun maniuallyCompleteTask(action: String, q: String, t: String?, p: ProxyCommandSender?, slient: Boolean): Any {

    if (p !is Player) return "fail,Reason is Sender is not a Player"
    val quest = questsMap[p]?.quests?.get(q) ?: return "fail,Player Quest is null"
    when (action) {
        "complete" -> {
            if (t == null) {
                // 完成任务
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = questTask.value.goal
                }
                quest.questStat = QuestStat.COMPLETED

                // Agent
                if (!slient) quest.addon.agent?.onComplete?.asList()?.let { runKether(it, p) }

            } else {
                quest.questTasks[t]?.progress = quest.questTasks[t]?.goal ?: 1
                if (!slient) quest.questTasks[t]?.addon?.agent?.onComplete?.asList()?.let { runKether(it, p) }

                return true
            }
        }

        "fail" -> {
            if (t == null) {
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = 0
                }
                quest.questStat = QuestStat.ACTIVE
                if (!slient) quest.addon.agent?.onFail?.asList()?.let { runKether(it, p) }
            } else {

                quest.questTasks[t]?.progress =
                    quest.questTasks[t]?.goal ?: 0
                if (!slient) quest.questTasks[t]?.addon?.agent?.onFail?.asList()?.let { runKether(it, p) }
            }
            return true
        }

        "accept" -> {
            quest.questStat = QuestStat.ACTIVE
            if (!slient) quest.addon.agent?.onAccept?.asList()?.let { runKether(it, p) }
            return true
        }

        "restart" -> {
            if (t == null) {
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = 0
                }
                quest.questStat = QuestStat.ACTIVE
                if (!slient) quest.addon.agent?.onRestart?.asList()?.let { runKether(it, p) }
            } else {

                quest.questTasks.get(t)?.progress =
                    quest.questTasks.get(t)?.goal ?: 0
                if (!slient) quest.questTasks[t]?.addon?.agent?.onRestart?.asList()?.let { runKether(it, p) }
            }
            return true
        }

        // return@actionNow maniuallyCompleteTask("complete"...)


    }
    return "fail,Unexpected error"
}
