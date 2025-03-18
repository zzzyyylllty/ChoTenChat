package io.github.zzzyyylllty.chotenchat.function.message

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
    for (part in configParts) {
        comp = comp.replace("{format-$part}", placeHolderConfig[part].toString())
    }

    warning(comp)
    return PatchedMessage(
        format = comp,
        message = this,
        receiveMode = receiver.asUserData()?.getReceiveMode(sender.fullId.getKID()) ?: ALWAYS
    )
}