package io.github.zzzyyylllty.melsydchat.command

import io.github.zzzyyylllty.melsydchat.command.subCommands.apiSubCommand
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.subCommand

/**
 * Usage: /dylsemchat
 *          ├── api
 *          │   ├── minimessage <content>
 *          │   └── TODO
 *          ├── group [groupID|RANDOM]
 *          │      ├── operation
 *          │      │      ├── create <owner>
 *          │      │      ├── transfer
 *          │      │      └── disband
 *          │      └── member <user>
 *          │              ├── permission <permissionGroup>
 *          │              ├── kick
 *          │              ├── mute <time>
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
 *          ├── announcement
 *          │      ├── send <group> <settings> <announcement>
 *          │      └── delete <group> <announcementUUID>
 *          ├── profilecard <user>
 *          │      ├── open
 *          │      └──
 *
 * */

@CommandHeader(
    name = "chotenchat",
    aliases = ["melsydchat","dylsemchat","ctc","chat"],
    permission = "chotenchat.command.main",
    description = "Main Command of ChoTenChat.",
    permissionMessage = "",
    permissionDefault = PermissionDefault.OP,
    newParser = false,
)
object ChoTenChatMainCommand {

    // 子节点
    @CommandBody
    val api = apiSubCommand
}