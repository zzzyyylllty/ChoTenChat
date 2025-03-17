package io.github.zzzyyylllty.melsydchat.function.contact.message

import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageReceiveMode
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.function.asUserData
import io.github.zzzyyylllty.melsydchat.function.contact.asContact
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.placeholderconfig
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player
import taboolib.common.platform.function.submitAsync
import taboolib.common.platform.function.warning
import taboolib.module.lang.asLangText

fun prepareSendMessage(message: Message) {
    submitAsync {
        val goal = message.goal
        var receiveMode: MessageReceiveMode = MessageReceiveMode.ALWAYS
        var user: User? = null
        val sender = message.sender

        when (goal) {
            is Group -> {
                goal.groupMember.entries.forEach { // 每个群内的用户都将收到消息
                    user = it.key.asContact(ContactType.USER) as User?
                    if (user == null) {
                        warning(console.asLangText("INTERNAL_USER_NOT_FOUND_BY_ID", it.key))
                    } else {
                        val receiveMode = if (
                            message.isMentioned(user) ||
                            user.getMessageReceiveMode(goal) == MessageReceiveMode.MENTION ||
                            user.asUserData()?.subscribeContact == goal.idData
                            ) {
                            // 被提及 / 特别关心 / 正在关注
                            MessageReceiveMode.MENTION
                        } else {
                            user.getMessageReceiveMode(goal)
                        }
                        val buildMessage = buildMessage(
                            message = message,
                            receiveMode = receiveMode,
                            user.asPlayer(),
                            sender
                        )
                    }
                }



                    receiveMode = if (message.sender.asUserData() == null) {
                        MessageReceiveMode.MENTION
                    } else {
                        user.asUserData()?.contactorSetting?.get(goal.idData)?.receiveMode ?: MessageReceiveMode.ALWAYS
                    }
                    }
                }

    }
}

fun buildMessage(message: Message, receiveMode: MessageReceiveMode, p: Player?, sender: User): String {
        val replacedContent = message.content
            .replace("{group}", safePapiReplace("group", p))
            .replace("{title}", safePapiReplace("title", p))
            .replace("{nick}", safePapiReplace("nick", p))
            .replace("{reply}", safePapiReplace("reply", p))
            .replace("{group.name}", sender.)
        return replacedContent
// PlaceholderAPI.setPlaceholders(p, replacedContent) DO NOT RESOLVE IT! %chemdah_command "op ..."% illegal op risk!
}

fun User.buildMessagePartOnly(message: Message, receiveMode: MessageReceiveMode): String {
    return message.content.replace("{message}", placeholderconfig["message-${receiveMode.name}"].toString())
}

fun safePapiReplace(from: String,p: Player?): String {
    return PlaceholderAPI.setPlaceholders(p, placeholderconfig[from].toString())
}