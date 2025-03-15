package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageType
import io.github.zzzyyylllty.melsydchat.data.UserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.config
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.jetbrains.annotations.Nullable
import taboolib.common.platform.function.severe
import taboolib.module.lang.asLangText
import java.util.*


/*
*
* return sourceDisplayName
                .append(Component.text(": "))
                .append(message);
* */

fun buildMessage(source: Player, sourceDisplayName: Component, message: Component, viewer: Audience,overrideUserData: UserData? = null,overrideMessage: Message? = null): Message {
    val formatChat = config["chat.format.chat"] as String
    val formatReply = config["chat.format.reply"] as String
    val formatSender = config["chat.format.sender"] as String
    
    val sourceAsUser = userMap[source.uniqueId]
    val sourceData = if (overrideUserData == null) userDataMap[sourceAsUser] else overrideUserData
    if (sourceAsUser == null) {
        severe(console.asLangText("INTERNAL_USER_NOT_FOUND", sourceAsUser.toString()))
        throw NullPointerException(sourceAsUser)
    }
    if (sourceData == null) {
        severe(console.asLangText("INTERNAL_USERDATA_NOT_FOUND", sourceAsUser.toString()))
        throw NullPointerException(sourceData)
    }

    val uuid = UUID.randomUUID()

    val message = Message(
        uuid = uuid,
        sendGoal = sourceData.subscribeContact,
        type = MessageType.TEXT,
        meta = LinkedHashMap(),
        content = TODO(),
        sendedTime = Calendar.getInstance(),
        reply = TODO()
    )
}

fun sendMessage(source: Player, sourceDisplayName: Component, message: Component, viewer: Audience) {
    
}