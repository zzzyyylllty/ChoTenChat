package io.github.zzzyyylllty.chotenchat.command

import ink.ptms.adyeshach.core.entity.type.minecraftVersion
import io.github.zzzyyylllty.chotenchat.command.subCommands.ChoTenChatApiCommand
import io.github.zzzyyylllty.chotenchat.command.subCommands.ChoTenChatDebugCommand
import io.github.zzzyyylllty.chotenchat.function.internalMessage.sendInternalMessages
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.severeL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.pluginVersion
import taboolib.common.platform.function.runningPlatform
import taboolib.expansion.createHelper
import taboolib.platform.util.asLangText

/**
 * Usage: /dylsemchat,chotenchat
 *          ├── about
 *          ├── api
 *          │   ├── minimessage <content>
 *          │   ├── eval <script>
 *          │   ├── evalByPlayer <player> <script>
 *          ├── debug
 *          │   ├── getUser <player>
 *          │   ├── getUserMap
 *          │   ├── diagnose
 *          │   └── getUserData <userLongID>
 *          ├── group [groupID|RANDOM]
 *          │      ├── operation
 *          │      │      ├── create <owner>
 *          │      │      ├── transfer
 *          │      │      └── disband
 *          │      └── member <user>
 *          │              ├── permission <permissionGroup>
 *          │              ├── kick
 *          │              ├── mute <time>
 *          │              ├── title
 *          │              │   ├── special <Title>
 *          │              │   └── special (clear)
 *          │              └── blacklist
 *          ├── message <group>
 *          │      ├── send <message>
 *          │      ├── reply <UUID> <message>
 *          │      ├── essence
 *          │      │   ├── add <UUID>
 *          │      │   └── remove <UUID>
 *          │      ├── collect
 *          │      │   ├── add <UUID>
 *          │      │   └── remove <UUID>
 *          │      ├── recall <UUID>
 *          │      └── copy <UUID>
 *          ├── announcement <group>
 *          │      ├── send <settings> <announcement>
 *          │      └── delete <announcementUUID>
 *          ├── profile <user|(self)>
 *          │      ├── open
 *          │      └── modify
 *
 * */

@CommandHeader(
    name = "chotenchat",
    aliases = ["dylsemchat","ctc","chat"],
    permission = "chotenchat.command.main",
    description = "Main Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatMainCommand {


    @CommandBody
    val about = subCommand {
        execute<CommandSender> { sender, context, argument -> //
            sender.sendInternalMessages("<gradient:#aaccff:#cc66ff:#ff66aa>ChoTenChat / DylsemChat</gradient> <#eeffaa>$pluginVersion")
            sender.sendInternalMessages("<gradient:#6600ff:#aa00aa>Running on Platform:</gradient> <light_purple>${runningPlatform.name} - $minecraftVersion")
            sender.sendInternalMessages("<#660099>Plugin by AkaCandyKAngel.")
            sender.sendInternalMessages("<#660099>Use <blue>/chotenchat help</blue> for help.")
        }
    }
    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val help = subCommand {
        createHelper()
    }

    @CommandBody
    val api = ChoTenChatApiCommand

    @CommandBody
    val debug = ChoTenChatDebugCommand

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, context, argument ->
            infoL("INTERNAL_INFO_RELOADING")
            sender.sendInternalMessages(sender.asLangText("INTERNAL_INFO_RELOADING"))
            try {
                ChoTenChat.reloadCustomConfig()
                sender.sendInternalMessages(sender.asLangText("INTERNAL_INFO_RELOADED"))
                infoL("INTERNAL_INFO_RELOADED")
            } catch (e: Throwable) {
                severeL("INTERNAL_SEVERE_RELOAD_ERROR")
                sender.sendInternalMessages(sender.asLangText("INTERNAL_SEVERE_RELOAD_ERROR"))
                e.printStackTrace()
            }
        }
    }
}