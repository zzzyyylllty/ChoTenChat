package io.github.zzzyyylllty.melsydchat.function.contact.message

import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.Friend
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageReceiveMode
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.function.asUserData
import io.github.zzzyyylllty.melsydchat.function.contact.asContact
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.placeholderconfig
import taboolib.common.platform.function.submitAsync
import taboolib.common.platform.function.warning
import taboolib.module.lang.asLangText

fun prepareSendMessage(message: Message) {
    submitAsync {
        val goal = message.goal
        var receiveMode: MessageReceiveMode = MessageReceiveMode.ALWAYS
        var user: User? = null

        when (goal) {
            is Group -> {
                goal.groupMember.entries.forEach { // 每个群内的用户都将收到消息
                    user = it.key.asContact(ContactType.USER) as User?
                    if (user == null) {
                        warning(console.asLangText("INTERNAL_USER_NOT_FOUND_BY_ID", it.key))
                    } else {
                        val buildMessage = user.buildMessage()
                    }
                }



                    receiveMode = if (message.sender.asUserData() == null) {
                        MessageReceiveMode.MENTION
                    } else {
                        user.asUserData()?.contactorSetting?.get(goal.idData)?.receiveMode ?: MessageReceiveMode.ALWAYS
                    }
                    }
                }

        /*
        when (goal) {
            is Group -> {
                goal.groupMember.entries.forEach {
                    user = it.key.asContact(ContactType.GROUP) as User
                    receiveMode = if (
                        message.sender.asUserData() == null
                    //message.sender.asUserData()?.contactorSetting[goal.uuid]?.receiveMode == MessageReceiveMode.MENTION
                    ) {
                        // 如果发送者是接收者的特别关心
                        MessageReceiveMode.MENTION
                    } else {user.asUserData()?.contactorSetting?.get(goal.idData)?.receiveMode ?: MessageReceiveMode.ALWAYS
                    }
                }
            }

            is Friend -> {
                user = goal.friendUser
                receiveMode = (user.asUserData()?.contactorSetting?.get(goal.idData)?.receiveMode ?: MessageReceiveMode.ALWAYS)
            }

            else -> {
                throw IllegalStateException("goal Is not a friend or Group")
            }
        }

         */
    }
}

fun User.buildMessage(message: Message, receiveMode: MessageReceiveMode) {
    submitAsync {
        val replacedContent = message.content
            .replace("{group}", placeholderconfig["group"].toString())
            .replace("{title}", placeholderconfig["title"].toString())
            .replace("{nick}", placeholderconfig["nick"].toString())

    }
}