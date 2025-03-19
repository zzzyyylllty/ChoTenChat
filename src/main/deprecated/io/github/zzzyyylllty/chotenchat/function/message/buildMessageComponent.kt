package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Contact
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.ReceiveMode.*
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.PatchedMessage
import io.github.zzzyyylllty.chotenchat.function.contact.asContact
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import taboolib.common.platform.function.warning

fun PatchedMessage.buildComponent(): Component {
    val mm = MiniMessage.miniMessage()
    var message = this.format.replace("{format-message}",
        ((placeHolderConfig["message-${this.receiveMode.name}"] ?: "<white>** {message.message} <hover:show_text:'<gray>{message.time} <yellow>点击管理本条消息...'><#886688><b>≡</hover>")).toString()
    )
    message = message.replace("{message.message}", "<yellow>${this.message.content}")

    return mm.deserialize(message.substring(0, message.length-1)) // 修复空行 fix null line
}

fun Message.patch(receiver: User): PatchedMessage {
    var comp = config["chat.format.${this.sendGoalContact.asContact()?.fullId?.type?.name}"].toString()
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

    val contactAsGroup = this.sendGoalContact.asContact()
    if (contactAsGroup is Group) {
        val member: Member? = contactAsGroup.members.get(sender.fullId.UIDData.numberUID)
        comp.replace("{group.name}", contactAsGroup.getName())
        comp.replace("{group.number}", contactAsGroup.fullId.UIDData.numberUID.toString())
        comp.replace("{group.shortname}", contactAsGroup.getShortName())
        comp.replace("{group.color}", contactAsGroup.getIdColor())
        comp.replace("{title.score}", member?.temperature.toString())
        comp.replace("{title.color}", "<white>") // TODO
        comp.replace("{title.level}", contactAsGroup.getTemperatureTitle(member?.getTempLevel() ?: 1))
        comp.replace("{title.special}", member?.specialTitle ?: "无")
        comp.replace("{title.permission}", member?.groupPermission?.name ?: "UNKNOWN")
        comp.replace("{title.title}", member?.getTitle() ?: "UNKNOWN")
    }

    warning(this)
    return PatchedMessage(
        format = comp,
        message = this,
        receiveMode = receiver.asUserData()?.getReceiveMode(sender.fullId.getKID()) ?: ALWAYS
    )
}