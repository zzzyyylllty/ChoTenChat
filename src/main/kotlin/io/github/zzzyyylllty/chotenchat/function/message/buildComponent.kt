package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.minimessage.MiniMessage
import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.model.user.User
import org.bukkit.entity.Player
import taboolib.module.lang.asLangText

/**
 * 本动作适用且只适用于玩家当前所在的后端服务器。
 * */
fun Message.buildGroupCompString() : String {

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
    
    val replace = mapOf<String,String>("{group.name}" to contactAsGroup.getNickOrReg(),
        "{group.number}" to contactAsGroup.longId.toString(),
        "{group.shortname}" to contactAsGroup.getShortName(),
        "{group.color}" to contactAsGroup.getIdColor(),
        "{title.score}" to member?.temperature.toString(),
        "{title.color}" to "<white>", // TODO
        "{title.level}" to contactAsGroup.getTemperatureTitle(member?.getTempLevel() ?: 1),
        "{title.special}" to (member?.specialTitle ?: console.asLangText("STRING_NONE")),
        "{title.permission}" to (member?.groupPermission?.name ?: console.asLangText("STRING_UNKNOWN")),
        "{title.title}" to (member?.getTitle(contactAsGroup) ?: console.asLangText("STRING_UNKNOWN")),
        "{nick.nick}" to sender.getGroupOrNickOrReg(member),
        "{nick.playername}" to sender.playerName,
        "{nick.playeruuid}" to sender.playerUUID,
        "{nick.namecolor}" to "<$nameColor>",
        "{nick.permission}" to primaryGroup.toString(),
        "{message.message}"
    )

    return comp

}