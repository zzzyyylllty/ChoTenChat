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
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player
import kotlin.collections.set


fun createOrWipeGroup(id: Long, creator: User, regName: String, nickName: String = regName, idData: IdData) {
    var members = linkedMapOf<Long, Member>(creator.longId to creator.asMember(id, GroupPermission.OWNER))

    loadedGroupMap[id] = Group(
        registryName = regName,
        nickName = nickName,
        longId = id,
        members = members,
        temperatureTitleLevel = linkedMapOf(0 to "测试"),
        idData = idData
    )
}

fun Long.asGroup(): Group? {
    return loadedGroupMap[this] ?: SQLDataBase().getGroupInDatabase(this)
}

fun Long.asUserOrFail(): Group {
    return this.asGroup() ?: throw NullPointerException("Could not found user for Group $this")
}
