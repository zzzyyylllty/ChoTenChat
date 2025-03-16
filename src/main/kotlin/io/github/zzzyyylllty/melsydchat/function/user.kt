package io.github.zzzyyylllty.melsydchat.function

import io.github.zzzyyylllty.melsydchat.data.ContactIDContainer
import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.entity.Player
import kotlin.Long
import kotlin.collections.get

fun Player.asUser(): User? {
    return userMap[this?.idData]
}

fun User?.asUserData(): UserData? {
    return userDataMap[this?.idData]
}

fun Player.wipeOrCreateUserData() {
    val player = this
    val playerId: ContactIDContainer = ContactIDContainer(1000000, ContactType.USER)
    val user = User(
        idData = playerId,
        name = player.name,
        avatar = null,
        playerUUID = player.uniqueId,
        nickName = player.name,
    )
    userMap[playerId] = user
    userDataMap[playerId] = UserData(
        subscribeContact = ContactIDContainer(1000000, ContactType.GROUP),
        contactorSetting = LinkedHashMap(),
    )
}