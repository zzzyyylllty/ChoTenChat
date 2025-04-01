package io.github.zzzyyylllty.chotenchat.function.contactOperatrion

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.infoS
import io.github.zzzyyylllty.chotenchat.logger.severeS
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.consoleSender
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.util.random
import taboolib.platform.util.asLangText
import kotlin.collections.set


fun createGroup(id: Long, creator: User, regName: String, nickName: String = regName, idData: IdData,sender: CommandSender = consoleSender) {
    if (id.asGroup() == null) {
        createOrWipeGroup(id, creator, regName, nickName, idData, sender)
    } else sender.severeS(consoleSender.asLangText("GROUP_CREATE_FAIL_EXISTED", id))
}

fun createOrWipeGroup(id: Long, creator: User, regName: String, nickName: String = regName, idData: IdData,sender: CommandSender = consoleSender) {
    var members = linkedMapOf<Long, Member>(creator.longId to creator.asMember(id, GroupPermission.OWNER))

    loadedGroupMap[id] = Group(
        registryName = regName,
        nickName = nickName,
        longId = id,
        members = members,
        temperatureTitleLevel = linkedMapOf(0 to "测试"),
        idData = idData
    )
    sender.infoS(sender.asLangText("GROUP_CREATED", id, regName, creator), true)
}

fun Long.asGroup(): Group? {
    return loadedGroupMap[this] ?: SQLDataBase().getGroupInDatabase(this)
}

fun Long.asUserOrFail(): Group {
    return this.asGroup() ?: throw NullPointerException("Could not found user for Group $this")
}

fun generateRandomGroupId() : Long{
    var randomId = 0
    while (true) {
        randomId = random(100000,999999)
        if (randomId.toLong().asGroup() == null) break
    }
    return randomId.toLong()
}