package io.github.zzzyyylllty.chotenchat.function.contact

import io.github.zzzyyylllty.chotenchat.data.Contact
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.logger.warningL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player


fun Player.asUserOrFail(): User? {
    return userMap[playerAsUserMap[this.uniqueId]] ?: run {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", this.name, this.uniqueId)
        throw NullPointerException()
    }
}

fun String.asContact(): Contact? {
    return if (this.startsWith("USER")) {
        userMap[this]
    } else if (this.startsWith("GROUP")) {
        this.getGroup()
    } else {
        throw IllegalStateException("Contact must be USER or GROUP")
    }
}
fun String.getGroup(): Group? {
    return if (loadedGroupMap.containsKey(this.split("-")[1])) {
        loadedGroupMap[this]
    } else {
        loadedGroupMap[this] // TODO DATABASE LOAD
    }
}
