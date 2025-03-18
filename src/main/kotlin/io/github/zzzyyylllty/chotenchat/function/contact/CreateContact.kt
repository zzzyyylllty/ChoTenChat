package io.github.zzzyyylllty.chotenchat.function.contact

import io.github.zzzyyylllty.chotenchat.data.ContactType
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.FullID
import io.github.zzzyyylllty.chotenchat.data.Group
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
                numberUID = "1",
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
fun createGroup(id :String, nickName :String,registryName: String) {
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
        )
    )
    loadedGroupMap[group.fullId.getKID()] = group
}