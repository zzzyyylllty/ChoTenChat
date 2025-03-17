package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.ReceiveMode.*
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.patchedMessage
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.config
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.placeholderconfig
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun patchedMessage.buildComponent(): Component {
    val mm = MiniMessage.miniMessage()
    var message = this.format.replace("{message}",
        (placeholderconfig?.get("message-${this.receiveMode.name}")
            ?: "<white>** {message.message} <hover:show_text:'<gray>{message.time} <yellow>点击管理本条消息...'><#886688><b>≡</hover>"
    ).toString()
    )
    message = message.replace("{message.message}", "<yellow>${this.message.content}")


    return mm.deserialize(message)
}

fun Message.patch(receiver: User): patchedMessage {
    val comp = this.content
    return patchedMessage(
        format = comp,
        message = this,
        receiveMode = receiver.asUserData()?.getReceiveMode(sender.fullId.getKID()) ?: ALWAYS
    )
}