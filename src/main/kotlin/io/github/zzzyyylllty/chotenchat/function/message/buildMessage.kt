package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.ReceiveMode.*
import io.github.zzzyyylllty.chotenchat.data.User
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * 本动作适用且只适用于玩家当前所在的后端服务器。
 * */
fun Message.buildMessage(string: String,receiver: User) : String {

    val mm = MiniMessage.miniMessage()
    val mentionedUser = null // TODO

    val sender = this.sender

    val replace = receiver.data.contactSettings[sender]?.receiveMode ?: "ALWAYS"

    return string.replace("{format-message}", placeHolderConfig.getString("message-$replace") ?: "NULL").replace("{message.message}", this.content)

}

