package io.github.zzzyyylllty.melsydchat.function.boot

import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.function.wipeOrCreateUserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.Bukkit
import java.util.UUID

fun initializeData() {
    userDataMap = LinkedHashMap<Long, UserData>()
    userMap = LinkedHashMap<Long, User>()
    Bukkit.getPlayer("zzzyyylll_ty")?.wipeOrCreateUserData()
}
