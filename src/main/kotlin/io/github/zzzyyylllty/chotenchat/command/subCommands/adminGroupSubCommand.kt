package io.github.zzzyyylllty.chotenchat.command.subCommands

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asOrCreateUser
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.addTemperature
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.changeSpecialTitle
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createGroup
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.generateRandomGroupId
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.getMember
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.setTemperature
import io.github.zzzyyylllty.chotenchat.function.indexfunc.createGroupInIndex
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
import taboolib.common.platform.command.player
import taboolib.platform.util.nextChat
import taboolib.platform.util.nextChatInTick
import java.util.UUID
import kotlin.text.toLong

@CommandHeader(
    name = "chotenchatadmingroup",
    aliases = ["dylsemchatadmingroup","ctcadmingroup","chatadmingroup"],
    permission = "chotenchat.command.admin.group",
    description = "ADMIN Group Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatAdminGroupCommand {

    @CommandBody
    val addTemperature = subCommand {
        dynamic("member_id") {
            dynamic("group_id") {
                dynamic("value") {
                    execute<CommandSender> { sender, context, argument ->
                        getMemberInContext(context)?.addTemperature(context["value"].toLong())
                    }
                }
            }
        }
    }
    @CommandBody
    val setTemperature = subCommand {
        dynamic("member_id") {
            dynamic("group_id") {
                dynamic("value") {
                    execute<CommandSender> { sender, context, argument ->
                        getMemberInContext(context)?.setTemperature(context["value"].toLong())
                    }
                }
            }
        }
    }

    @CommandBody
    val changeSpecialTitle = subCommand {
        dynamic("member_id") {
            dynamic("group_id") {
                dynamic("value") {
                    execute<CommandSender> { sender, context, argument ->
                        getMemberInContext(context)?.changeSpecialTitle(context["value"])
                    }
                }
            }
        }
    }

}

fun getMemberInContext(context: CommandContext<CommandSender>): Member? {
    return context["member_id"].toLong().asUser()?.getMember(context["group_id"].toLong())
}
