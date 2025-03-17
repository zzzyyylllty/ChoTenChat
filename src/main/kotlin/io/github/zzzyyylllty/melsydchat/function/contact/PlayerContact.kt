package io.github.zzzyyylllty.melsydchat.function.contact

import io.github.zzzyyylllty.melsydchat.data.Contact
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.logger.warningL
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.entity.Player


fun Player.asUserOrFail(): User? {
    return userMap[playerAsUserMap[this.uniqueId]] ?: run {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", this.name, this.uniqueId)
        throw NullPointerException()
    }
}

fun String.asContact(): Contact? {
    if (this.startsWith("USER")) {
        return userMap[this]
    } else if (this.startsWith("GROUP")) {
        return this.getGroup()
    } else {
        throw IllegalStateException("Contact must be USER or GROUP")
    }
}
fun String.getGroup(): Group? {
    return if (loadedGroupMap.containsKey(this)) {
        loadedGroupMap[this]
    } else {
        loadedGroupMap[this] // TODO DATABASE LOAD
    }
}
