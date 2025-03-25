package io.github.zzzyyylllty.chotenchat.function.contactOperatrion

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.TitleSelection
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.logger.infoL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player
import kotlin.collections.set


fun Player.createOrWipeUser() {

    val player = this
    val user = User(
        registryName = player.name,
        nickName = null,
        longId = 1000000,
        idData = IdData(
            fancyAccountType = FancyAccountType.NORMAL,
            fancyAccountValue = 0
        ),
        playerUUID = player.uniqueId.toString(),
        playerName = player.name,
        data = UserData(
            subscribeContact = null,
            contactSettings = LinkedHashMap()
        )
    )
    val id = user.longId
    userMap[id] = user
    playerAsUserMap[this.uniqueId] = id
    infoL("INTERNAL_INFO_CREATING_USER", this.name ,user)
}

fun User.asMember(g: Long, permission: GroupPermission) : Member {
    return Member(
        groupName = this.getNickOrReg(),
        longId = longId,
        group = g,
        playerUUID = playerUUID,
        playerName = playerName,
        temperature = 0,
        specialTitle = null,
        groupPermission = permission,
        titleSelection = TitleSelection.PERMISSION,
        titleTheme = "default"
    )
}