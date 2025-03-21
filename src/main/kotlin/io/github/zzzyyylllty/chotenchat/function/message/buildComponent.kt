package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Contact
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.User
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun Message.buildGroupCompString() : String {

    val mm = MiniMessage.miniMessage()
    val mentionedUser = null // TODO

    val contactAsGroup = subscribeContact as Group
    var comp = config["chat.format.GROUP"].toString()
    val configParts = placeHolderConfig.getValues(false).keys
    /*
    * # {group.name} {group.number} {group.color} {group.shortname}
    * # {title.score} {title.color} {title.level} {title.special} {title.permission} {title.title}
    * # {nick.nick} {nick.playername} {nick.coloredlevel} {nick.level} {nick.permission}
    * # {message.message} {message.time} {reply.sender} {reply.message}
    * */
    for (part in configParts) {
        comp = comp.replace("{format-$part}", placeHolderConfig[part].toString())
    }

    val member: Member? = contactAsGroup.members.get(sender.longId)

    if (member == null) {

    }

    comp = comp.replace("{group.name}", contactAsGroup.getNickOrReg())
    .replace("{group.number}", contactAsGroup.longId.toString())
    .replace("{group.shortname}", contactAsGroup.getShortName())
    .replace("{group.color}", contactAsGroup.getIdColor())
    .replace("{title.score}", member?.temperature.toString())
    .replace("{title.color}", "<white>") // TODO
    .replace("{title.level}", contactAsGroup.getTemperatureTitle(member?.getTempLevel() ?: 1))
    .replace("{title.special}", member?.specialTitle ?: "无")
    .replace("{title.permission}", member?.groupPermission?.name ?: "Unknown")
    .replace("{title.title}", member?.getTitle(contactAsGroup) ?:"Unknown")

    return comp

}