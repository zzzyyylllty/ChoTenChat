package io.github.zzzyyylllty.zaleplon.commands

import io.github.zzzyyylllty.zaleplon.functions.returnPlayerOnlyTargetSelector
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.asLangText
import taboolib.platform.util.sendLang

@CommandHeader("zaleplon", ["zale", "zp"], permission = "zaleplon.command")
object MainCommand {

    // 子节点
    @CommandBody
    val operationQuest = subCommand {
        val modifyPlayerQuestStat = subCommand {

            // 参数 user
            dynamic("players") {
                dynamic("quest") {
                    dynamic("modify") {
                        execute<CommandSender> { sender, context, argument ->
                            // 获取参数的值
                            for (p in Bukkit.selectEntities(sender, context["players"])) {
                                if (p is Player) {
                                    val q = context["players"]
                                    if (context["modify"].startsWith("quest:"))

                                        if (context["modify"].startsWith("task:")) {
                                        }

                                } else {
                                    sender.asLangText("DEBUG_NON_PLAYER_TARGET_SELECTOR", context["players"])
                                }
                            }
                            sender.sendLang("COMMAND_NO_PARAMS", "modify")
                        }
                    }
                    execute<CommandSender> { sender, context, argument ->
                        sender.sendLang(
                            "COMMAND_NO_PARAMS",
                            "modify"
                        )
                    }
                }
                execute<CommandSender> { sender, context, argument ->
                    sender.sendLang(
                        "COMMAND_NO_PARAMS",
                        "quest, modify"
                    )
                }
                suggestion<CommandSender>(true) { sender, context -> returnPlayerOnlyTargetSelector() }
            }
        }


    }
}