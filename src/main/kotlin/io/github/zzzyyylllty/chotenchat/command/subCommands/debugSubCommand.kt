package io.github.zzzyyylllty.chotenchat.command.subCommands

import com.beust.klaxon.JsonArray
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.asGroup
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.subscribeContact
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.tabooPlayerAsUser
import io.github.zzzyyylllty.chotenchat.logger.infoS
import io.github.zzzyyylllty.chotenchat.logger.warningS
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
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
import kotlinx.serialization.encodeToString
import kotlin.text.toLong

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
                    sender.infoS(sender.asLangText("PLAYER_NOT_FOUND", context.player("player")))
                    return@execute
                }
                val user = tabooPlayerAsUser(sender, context)
                if (user == null) {
                    sender.infoS(sender.asLangText("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", bukkitPlayer.name, bukkitPlayer.uniqueId))
                    return@execute
                }
                val data = user.data
                sender.infoS(sender.asLangText("INTERNAL_DEBUG_USER", Json.encodeToString(user)))
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
            sender.infoS(message)
        }
    }

    @CommandBody
    val getUserDataMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            sender.warningS("Command deprecated.From v0.3 user & userdata is merged.")
        }
    }


    @CommandBody
    val getGroup = subCommand {
        dynamic("id") {
            execute<CommandSender> { sender, context, argument ->
                val id = context.get("id").toLong()
                var message = "<yellow><b>Group:</b> <br>${loadedGroupMap[id]}"
                sender.infoS(message)
            }
        }
    }


    @CommandBody
    val getGroupMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>GroupMap Entries</b> (${loadedGroupMap.size}):<br>"
            for (entry in loadedGroupMap.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${Json.encodeToString(entry.value)}"
            }
            sender.infoS(message)
        }
    }


    @CommandBody
    val getPlayerAsUserMap = subCommand {
        execute<CommandSender> { sender, context, argument ->
            var message = "<yellow><b>UserDataMap Entries</b> (${ChoTenChat.playerAsUserMap.size}):<br>"
            for (entry in ChoTenChat.playerAsUserMap.entries) {
                message = "$message<br><white>${entry.key} <gray>- ${Json.encodeToString(entry.value)}"
            }
            sender.infoS(message)
        }
    }

    @CommandBody
    val subscribe = subCommand {
        dynamic("type") {
            dynamic("id") {
                execute<CommandSender> { sender, context, argument ->
                    val id = context.get("id").toLong()
                    val type = context.get("type")
                    if (sender !is Player) throw IllegalStateException("Sender Type must be Player")
                    when (type) {
                        "GROUP" -> id.asGroup()?.let { sender.asUser()?.subscribeContact(it) }
                        "USER" -> id.asUser()?.let { sender.asUser()?.subscribeContact(it) }
                    }
                }
                suggestion<CommandSender>(uncheck = true) { sender, context ->
                    suggesstionGroupOrUser(context.get("type"))
                }
            }
            suggestion<CommandSender>(uncheck = false) { sender, context ->
                listOf("GROUP","USER")
            }
        }
    }
}

fun suggesstionGroupOrUser(type: String): List<String> {
    return when (type) {
        "GROUP" -> {
            val list = mutableListOf<String>()
            loadedGroupMap.keys.toList().forEach { list.add(it.toString()) }
            list
        }
        "USER" -> {
            val list = mutableListOf<String>()
            userMap.keys.toList().forEach { list.add(it.toString()) }
            list
        }
        else -> {listOf<String>()}
    }
}