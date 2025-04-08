package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.dateTimeFormatter
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.minimessage.MiniMessage
import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.model.user.User
import org.bukkit.entity.Player
import taboolib.module.lang.asLangText
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

/**
 * 本动作适用且只适用于玩家当前所在的后端服务器。
 * */
fun Message.buildCompString() : String {

    val mm = MiniMessage.miniMessage()
    val mentionedUser = null // TODO

    val contactAsGroup = subscribeContact as Group
    var comp = config["chat.format.GROUP"].toString()
    val configParts = placeHolderConfig.getValues(false).keys
    /*
    * # {group.name} {group.number} {group.color} {group.shortname}
    * # {title.score} {title.color} {title.level} {title.special} {title.permission} {title.title}
    * # {nick.nick} {nick.playername} {nick.playeruuid} {nick.namecolor} {nick.permission}
    * # {message.message} {message.time} {reply.sender} {reply.message}
    * */
    if (this.reply == null) comp = comp.replace("{format-reply}", "")
    for (part in configParts) {
        comp = comp.replace("{format-$part}", placeHolderConfig[part].toString())
    }

    val member: Member? = contactAsGroup.members.get(sender.longId)


    if (member == null) {
        // TODO 踢出群聊提示
    }

    val lpApi = LuckPermsProvider.get();
    val user: User? = lpApi.userManager.getUser(uuid)

    val nameColor: String? = user?.getCachedData()?.getMetaData()?.getMetaValue("namecolor") ?: config["chat.default.default-namecolor"].toString()
    val primaryGroup: String? = user?.primaryGroup
    val chatColor: String? = user?.getCachedData()?.getMetaData()?.getMetaValue("chatcolor") ?: config["chat.default.default-chatcolor"].toString()

    val replySender = this.reply?.sender

    val replaces = mapOf<String,String>("{group.name}" to contactAsGroup.getNickOrReg(),
        "{group.number}" to contactAsGroup.longId.toString(),
        "{group.shortname}" to contactAsGroup.getShortName(),
        "{group.color}" to contactAsGroup.getIdColor(),
        "{title.score}" to member?.temperature.toString(),
        "{title.color}" to "<gradient:#ffff00:#ff0000>", // TODO
        "{title.level}" to (member?.getTempLevel() ?: "1").toString(),
        "{title.special}" to (member?.specialTitle ?: console.asLangText("STRING_NONE")),
        "{title.permission}" to (member?.groupPermission?.name ?: console.asLangText("STRING_UNKNOWN")),
        "{title.title}" to (member?.getTitle(contactAsGroup) ?: console.asLangText("STRING_UNKNOWN")),
        "{nick.nick}" to sender.getGroupOrNickOrReg(member),
        "{nick.playername}" to (sender.playerName ?: "Unknown"),
        "{nick.playeruuid}" to (sender.playerUUID ?: "Unknown"),
        "{nick.namecolor}" to "<$nameColor>",
        "{nick.color}" to "<gold>", // TODO
        "{nick.level}" to "100", // TODO
        "{nick.permission}" to primaryGroup.toString(),
        "{message.time}" to this.sendTime.format(dateTimeFormatter),
        "{reply.sender}" to (replySender?.getGroupOrNickOrReg(this.subscribeContact.members[replySender.longId]) ?: console.asLangText("STRING_UNKNOWN")),
        "{reply.message}" to (this.reply?.content ?: console.asLangText("STRING_UNKNOWN")))

    // 无需正则表达式优化，因为是反向优化
    for (replace in replaces) {
        comp = comp.replace(replace.key, replace.value)
    }



    return comp

}

