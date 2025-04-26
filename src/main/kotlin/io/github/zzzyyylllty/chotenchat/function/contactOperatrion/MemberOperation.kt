package io.github.zzzyyylllty.chotenchat.function.contactOperatrion

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.TitleSelection
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.infoS
import io.github.zzzyyylllty.chotenchat.logger.severeS
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.consoleSender
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.util.random
import taboolib.platform.util.asLangText
import kotlin.collections.set

fun Member.addTemperature(amount: Long) {
    temperature = temperature + amount
}

fun Member.setTemperature(amount: Long) {
    temperature = amount
}
fun Member.changeSpecialTitle(title: String) {
    specialTitle = title
}
fun Member.changeTitleSelect(selection: TitleSelection) {
    titleSelection = selection
}
fun Member.changePermission(permission: GroupPermission) {
    groupPermission = permission
}