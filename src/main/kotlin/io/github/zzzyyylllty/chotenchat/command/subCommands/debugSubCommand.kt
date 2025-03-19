package io.github.zzzyyylllty.chotenchat.command.subCommands

import io.github.zzzyyylllty.chotenchat.data.asUser
import io.github.zzzyyylllty.chotenchat.function.internalMessage.sendInternalMessages
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.asLangText

@CommandHeader(
    name = "chotenchatdebug",
    aliases = ["dylsemchatdebug","ctcd","chatdebug"],
    permission = "chotenchat.command.debug",
    description = "DEBUG Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatDebugCommand {

    /** 根据玩家名称获取用户和其数据
     *  Get [io.github.zzzyyylllty.chotenchat.data.User] & [io.github.zzzyyylllty.chotenchat.data.UserData] by [Player]
     * */
    @CommandBody
    val getUser = subCommand {
        player("player") {
            execute<CommandSender> { sender, context, argument ->
                val tabooPlayer = context.player("player")
                val bukkitPlayer = tabooPlayer.castSafely<Player>()
                val senderAsPlayer = sender as Player
                if (bukkitPlayer == null) {
                    sender.sendInternalMessages(sender.asLangText("INTERNAL_DIRECT_PLAYER_NOT_FOUND", context.player("player")))
                    return@execute
                }
                val user = bukkitPlayer.asUser()
                if (user == null) {
                    sender.sendInternalMessages(sender.asLangText("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", bukkitPlayer.name, bukkitPlayer.uniqueId))
                    return@execute
                }
                val data = user.asUserData()
                if (data == null) {
                    sender.sendInternalMessages(sender.asLangText("INTERNAL_USERDATA_NOT_FOUND", user))
                    return@execute
                }
                sender.sendInternalMessages(sender.asLangText("INTERNAL_DEBUG_USER", user, data))
            }
        }
    }

    @CommandBody
    val getUserMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>UserMap Entries</b> (${userMap.size}):<br>"
            for (entry in userMap.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${entry.value}"
            }
            sender.sendInternalMessages(message)
        }
    }

    @CommandBody
    val getUserDataMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>UserDataMap Entries</b> (${userDataMap.size}):<br>"
            for (entry in userDataMap.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${entry.value}"
            }
            sender.sendInternalMessages(message)
        }
    }


    @CommandBody
    val GroupMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>UserDataMap Entries</b> (${loadedGroupMap.size}):<br>"
            for (entry in loadedGroupMap.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${entry.value}"
            }
            sender.sendInternalMessages(message)
        }
    }


    @CommandBody
    val playerAsUserMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>UserDataMap Entries</b> (${playerAsUser.size}):<br>"
            for (entry in playerAsUser.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${entry.value}"
            }
            sender.sendInternalMessages(message)
        }
    }



}
