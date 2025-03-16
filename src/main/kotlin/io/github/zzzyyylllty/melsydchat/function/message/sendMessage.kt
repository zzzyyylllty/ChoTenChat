package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Friend
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageReceiveMode
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.function.asUserData
import taboolib.common.platform.function.submitAsync

fun sendMessage(message: Message) {
    submitAsync {
        val goal = message.goal
        var receiveMode: MessageReceiveMode = MessageReceiveMode.ALWAYS
        var user: User = message.sender
        when (goal) {
            is Group -> {
                goal.groupMember.entries.forEach {
                    user = it.key
                    receiveMode = if (
                        message.sender.asUserData() == null
                    //message.sender.asUserData()?.contactorSetting[goal.uuid]?.receiveMode == MessageReceiveMode.MENTION
                    ) {
                        // 如果发送者是接收者的特别关心
                        MessageReceiveMode.MENTION
                    } else {user.asUserData()?.contactorSetting?.get(goal.uuid)?.receiveMode ?: MessageReceiveMode.ALWAYS
                    }
                }
            }

            is Friend -> {
                user = goal.friendUser
                receiveMode = (user.asUserData()?.contactorSetting?.get(goal.uuid)?.receiveMode ?: MessageReceiveMode.ALWAYS)
            }

            else -> {
                throw IllegalStateException("goal Is not a friend or Group")
            }
        }

        if (receiveMode != MessageReceiveMode.HIDDEN) {
            submitAsync {
                user.sendMessage(message)
            }
        }

    }
}
