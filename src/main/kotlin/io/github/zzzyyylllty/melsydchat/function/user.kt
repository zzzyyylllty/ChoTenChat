package io.github.zzzyyylllty.melsydchat.function

import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.entity.Player

fun Player.asUser(): User? {
    return userMap[this.uniqueId]
}

fun User.asUserData(): UserData? {
    return userDataMap[this]
}
