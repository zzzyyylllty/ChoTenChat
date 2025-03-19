package io.github.zzzyyylllty.chotenchat.function.contact

import io.github.zzzyyylllty.chotenchat.data.ContactType
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.FullID
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.TitleSelection
import io.github.zzzyyylllty.chotenchat.data.UIDData
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.logger.infoL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player


fun Player.createOrWipeUser() {

    val user = User(
        registryName = this.name,
        nickName = this.displayName,
        fullId = FullID(
            type = ContactType.USER,
            UIDData = UIDData(
                numberUID = 1,
                fancyAccountType = FancyAccountType.ADMINISTRATOR,
                fancyAccountValue = 114514
            )
        ),
        uuid = this.uniqueId,
    )
    userMap[user.fullId.getKID()] = user
    playerAsUserMap[this.uniqueId] = user.fullId.getKID()
    infoL("INTERNAL_INFO_CREATING_USER", this.name ,user)
    user.createOrWipeData()
}

fun User.createOrWipeData() {
    userDataMap[this.fullId.getKID()] = UserData(
        contactSettings = linkedMapOf(),
        subscribedContact = "GROUP-1000000"
    )
}
fun createGroup(id : Long, nickName :String, registryName: String, members: LinkedHashMap<Long, Member>) {
    val group = Group(
        registryName = registryName,
        nickName = nickName,
        fullId = FullID(
            type = ContactType.GROUP,
            UIDData = UIDData(
                numberUID = id,
                fancyAccountType = FancyAccountType.ADMINISTRATOR,
                fancyAccountValue = 114514
            )
        ),
        members = members,
        temperatureTitleLevel = LinkedHashMap(
            mapOf(0 to "Unknow", 11 to "Candy",21 to "Rain",41 to "Ame", 61 to "P", 81 to "KAngel", 101 to "ChoTen")
        )
    )
    loadedGroupMap[group.fullId.getKID()] = group
}
fun User.cleanMember(permission: GroupPermission): Member {
    return Member(
        nickName = this.getName(),
        temperature = 0,
        specialTitle = null,
        groupPermission = permission,
        titleSelection = TitleSelection.TEMPERATURE
    )
}