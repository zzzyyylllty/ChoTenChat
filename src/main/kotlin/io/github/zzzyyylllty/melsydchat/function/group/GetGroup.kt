package io.github.zzzyyylllty.melsydchat.function.group

import io.github.zzzyyylllty.melsydchat.data.Contact
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.entity.Player

fun User.getSubscribeContact(): List<Contact>? {
    return userDataMap[this]?.subscribeContact
}

fun Player.asUser(): User? {
    return userMap[this.uniqueId]
}
