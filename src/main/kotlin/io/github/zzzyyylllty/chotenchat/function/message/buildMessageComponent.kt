package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.ReceiveMode.*
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.patchedMessage
import io.github.zzzyyylllty.chotenchat.function.contact.asContact
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import taboolib.common.platform.function.warning

fun patchedMessage.buildComponent(): Component {
    val mm = MiniMessage.miniMessage()
    var message = this.format.replace("{format-message}",
        ((placeHolderConfig["message-${this.receiveMode.name}"] ?: "<white>** {message.message} <hover:show_text:'<gray>{message.time} <yellow>点击管理本条消息...'><#886688><b>≡</hover>")
    ).toString()
    )
    message = message.replace("{message.message}", "<yellow>${this.message.content}")


    return mm.deserialize(message)
}

fun Message.patch(receiver: User): patchedMessage {
    var comp = config["chat.format.${this.sendGoalContact.asContact()?.fullId?.type?.name}"].toString()
    val configParts = placeHolderConfig.getValues(false).keys
    for (part in configParts) {
        comp = comp.replace("{format-$part}", placeHolderConfig[part].toString())
    }

    return patchedMessage(
        format = comp,
        message = this,
        receiveMode = receiver.asUserData()?.getReceiveMode(sender.fullId.getKID()) ?: ALWAYS
    )
}