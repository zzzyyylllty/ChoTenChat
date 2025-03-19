package io.github.zzzyyylllty.chotenchat.function.bukkitPlayer

import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player
import taboolib.expansion.getDataContainer

fun Player.asUser(): User? {
    return userMap[playerAsUserMap[this.uniqueId]] ?: null


}

fun Player.asUserOrFail(): User? {
    return this.asUser() ?: throw NullPointerException("Could not found user for Player ${this.name}")
}
