package io.github.zzzyyylllty.chotenchat.command.subCommands

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asOrCreateUser
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createGroup
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.generateRandomGroupId
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.tabooPlayerAsUser
import io.github.zzzyyylllty.chotenchat.function.kether.evalKether
import io.github.zzzyyylllty.chotenchat.logger.fineS
import io.github.zzzyyylllty.chotenchat.logger.infoS
import io.github.zzzyyylllty.chotenchat.logger.severeS
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandContext
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.submitAsync
import taboolib.platform.util.asLangText
import kotlinx.serialization.decodeFromString
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import java.util.UUID
import kotlin.text.toLong

@CommandHeader(
    name = "chotenchatapi",
    aliases = ["dylsemchatapi","ctca","chatapi","chotenapi"],
    permission = "chotenchat.command.api",
    description = "API Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatApiCommand {

    /** 解析 Minimessage */
    @CommandBody
    val minimessage = subCommand {
        dynamic("content") {
            execute<CommandSender> { sender, context, argument ->
                val mm = MiniMessage.miniMessage()
                // 获取参数的值
                val content = context["content"]
                sender.infoS(content)
            }
        }
    }

    /** Kether */
    @CommandBody
    val eval = subCommand {
        dynamic("script") {
            execute<CommandSender> { sender, context, argument ->
                val mm = MiniMessage.miniMessage()
                // 获取参数的值
                val content = context["script"]
                val ret = content.evalKether(sender)
                sender.fineS("<yellow>Return: <gray>${ret.get()}")
            }
        }
    }

    /** Kether */
    @CommandBody
    val evalByPlayer = subCommand {
        player("player") {
            dynamic("script") {
                execute<CommandSender> { sender, context, argument ->
                    val tabooPlayer = context.player("player")
                    val bukkitPlayer = tabooPlayer.castSafely<Player>()
                    val mm = MiniMessage.miniMessage()
                    // 获取参数的值
                    val content = context["script"]
                    val ret = content.evalKether(sender)
                    sender.fineS("<yellow>Return: <gray>${ret.get()}")
                }
            }
        }
    }
    /** Kether */
    @CommandBody
    val evalSilent = subCommand {
        dynamic("script") {
            execute<CommandSender> { sender, context, argument ->
                val mm = MiniMessage.miniMessage()
                // 获取参数的值
                val content = context["script"]
                content.evalKether(sender)
            }
        }
    }

    /** Kether */
    @CommandBody
    val evalByPlayerSilent = subCommand {
        player("player") {
            dynamic("script") {
                execute<CommandSender> { sender, context, argument ->
                    val tabooPlayer = context.player("player")
                    val bukkitPlayer = tabooPlayer.castSafely<Player>()
                    val mm = MiniMessage.miniMessage()
                    // 获取参数的值
                    val content = context["script"]
                    content.evalKether(sender)
                }
            }
        }
    }

    /** Create Group */
    @CommandBody
    val createGroup = subCommand {
        player("player") {
            dynamic("id") {
                dynamic("registryName") {
                    dynamic("fancytype") {
                        execute<CommandSender> { sender, context, argument ->
                            createGroupFromCommand(sender, context, argument)
                        }
                    }
                    execute<CommandSender> { sender, context, argument ->
                        createGroupFromCommand(sender, context, argument)
                    }
                }
                execute<CommandSender> { sender, context, argument ->
                    createGroupFromCommand(sender, context, argument)
                }
            }
        }
    }

    /** Create Group */
    @CommandBody
    val createGroupFromJson = subCommand {
        dynamic("json") {
            execute<CommandSender> { sender, context, argument ->
                val group = Json.decodeFromString<Group>(context["json"])
                loadedGroupMap[group.longId] = group
            }
        }
    }

    @CommandBody
    val createUser = subCommand {
        player("player") {
            dynamic("id") {
                dynamic("registryName") {
                    dynamic("fancytype") {
                        execute<CommandSender> { sender, context, argument ->
                            createUserFromCommand(sender, context, argument)
                        }
                    }
                    execute<CommandSender> { sender, context, argument ->
                        createGroupFromCommand(sender, context, argument)
                    }
                }
                execute<CommandSender> { sender, context, argument ->
                    createGroupFromCommand(sender, context, argument)
                }
            }
        }
    }

    /** Create Group */
    @CommandBody
    val createUserFromJson = subCommand {
        dynamic("json") {
            execute<CommandSender> { sender, context, argument ->
                val user = Json.decodeFromString<User>(context["json"])
                userMap[user.longId] = user
                playerAsUserMap[UUID.fromString(user.playerUUID)] = user.longId
            }
        }
    }
    /** Create Group */
    @CommandBody
    val profileCard = subCommand {
        dynamic("id") {
            execute<CommandSender> { sender, context, argument ->
                val user = Json.decodeFromString<User>(context["json"])
                userMap[user.longId] = user
                playerAsUserMap[UUID.fromString(user.playerUUID)] = user.longId
            }
        }
    }

}

fun createGroupFromCommand(sender: CommandSender, context: CommandContext<CommandSender>, argument: String) {
    submitAsync {
        val user = tabooPlayerAsUser(sender, context)
        val mm = MiniMessage.miniMessage()
        // 获取参数的值
        val id = context.getOrNull("id") ?: generateRandomGroupId()
        createGroup(
            id = context["id"].toLong(),
            creator = user,
            regName = context["registryName"],
            idData = IdData(
                fancyAccountType = FancyAccountType.valueOf(context.getOrNull("fancytype") ?: "NORMAL"),
                fancyAccountValue = 0
            ),
            sender = sender
        )
    }
}
fun createUserFromCommand(sender: CommandSender, context: CommandContext<CommandSender>, argument: String) {
    submitAsync {
        val tabooPlayer = context.player("player")
        val bukkitPlayer = tabooPlayer.castSafely<Player?>()
        val user = bukkitPlayer.asOrCreateUser()
        val mm = MiniMessage.miniMessage()
        // 获取参数的值
        val id = context.getOrNull("id") ?: generateRandomGroupId()
    }
}