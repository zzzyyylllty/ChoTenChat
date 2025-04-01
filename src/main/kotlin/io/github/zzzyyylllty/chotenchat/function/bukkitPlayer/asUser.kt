package io.github.zzzyyylllty.chotenchat.function.bukkitPlayer

import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createOrWipeUser
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player
import taboolib.expansion.getDataContainer

fun Player.asUser(): User? {
    return userMap[playerAsUserMap[this.uniqueId]] ?: SQLDataBase().getUserInDatabase(this)
}

fun Player.asOrCreateUser(): User {
    return (userMap[playerAsUserMap[this.uniqueId]] ?: SQLDataBase().getUserInDatabase(this)) ?: this.createOrWipeUser()
}

fun Player.asUserOrFail(): User {
    return this.asUser() ?: throw NullPointerException("Could not found user for Player ${this.name}")
}


fun Long.asUser(): User? {
    return userMap[this] ?: SQLDataBase().getUserInDatabase(this)
}

fun Long.asUserOrFail(): User {
    return this.asUser() ?: throw NullPointerException("Could not found user for ID ${this}")
}
