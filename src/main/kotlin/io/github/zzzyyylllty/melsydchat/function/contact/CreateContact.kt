package io.github.zzzyyylllty.melsydchat.function.contact

import io.github.zzzyyylllty.melsydchat.data.Contact
import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.FancyAccountType
import io.github.zzzyyylllty.melsydchat.data.FullID
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.UIDData
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.logger.infoL
import io.github.zzzyyylllty.melsydchat.logger.warningL
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
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