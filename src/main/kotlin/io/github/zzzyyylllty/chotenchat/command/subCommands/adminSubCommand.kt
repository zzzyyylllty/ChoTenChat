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
import io.github.zzzyyylllty.chotenchat.function.indexfunc.createGroupInIndex
import io.github.zzzyyylllty.chotenchat.function.kether.evalKether
import io.github.zzzyyylllty.chotenchat.logger.fineS
import io.github.zzzyyylllty.chotenchat.logger.infoS
import io.github.zzzyyylllty.chotenchat.logger.severeS
import io.papermc.paper.event.player.AsyncChatEvent
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
import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.command.player
import taboolib.platform.util.nextChat
import taboolib.platform.util.nextChatInTick
import java.util.UUID
import kotlin.text.toLong

@CommandHeader(
    name = "chotenchatadmin",
    aliases = ["dylsemchatadmin","ctcadmin","chatadmin"],
    permission = "chotenchat.command.admin",
    description = "ADMIN Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatAdminCommand {

    /** 创建群聊 */
    @CommandBody
    val createGroup = subCommand {
        submitAsync {
            dynamic("content") {
                execute<CommandSender> { sender, context, argument ->
                    val mm = MiniMessage.miniMessage()
                    // 获取参数的值
                    val content = context["content"]
                    sender.infoS(content)
                }
            }
        }
    }
    /** 创建群聊 - 索引模式 */
    @CommandBody
    val createGroupIndex = subCommand {
        execute<CommandSender> { sender, context, argument ->
            (sender as Player).createGroupInIndex()
        }
    }


@CommandBody
val group = ChoTenChatAdminGroupCommand
}
