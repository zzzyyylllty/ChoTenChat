package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.ReceiveMode.*
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.patchedMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun buildMessageComponent(patchedMessage: patchedMessage, receiver: User): Component {
    val mm = MiniMessage.miniMessage()
    var message =
        when (patchedMessage.receiveMode) {
            SUBSCRIBE ->
                patchedMessage.format.replace("{message}", "<yellow>${patchedMessage.message.content}")

            ALWAYS ->
                patchedMessage.format.replace("{message}", "<white>${patchedMessage.message.content}")

            NOTE ->
                patchedMessage.format.replace("{message}", "<white>${patchedMessage.message.content}")

            HIDDEN -> throw IllegalStateException("HIDDEN receive mode should not be called in patched.")
        }

    return mm.deserialize(message)
}

fun Message.patch(): patchedMessage {
    val comp = this.content
    return patchedMessage(
        format = comp,
        message = this,
        receiveMode = TODO()
    )
}