package io.github.zzzyyylllty.melsydchat.function.message

import io.github.zzzyyylllty.melsydchat.data.Contact
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.Message
import io.github.zzzyyylllty.melsydchat.data.MessageType
import io.github.zzzyyylllty.melsydchat.data.NullableSectionsMessage
import io.github.zzzyyylllty.melsydchat.data.UserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.config
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
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

fun buildMessage(source: Player, sourceDisplayName: Component, message: Component, viewer: Audience, overrideUserData: UserData? = null, subscribedContact: Contact, overrideMessage: NullableSectionsMessage? = null): Message {

    val formatChat = config["chat.format.chat"] as String
    val formatReply = config["chat.format.reply"] as String
    val formatSender = config["chat.format.sender"] as String
    val formatMessage = config["chat.format.format-message"] as String

    val mm = MiniMessage.miniMessage()
    val sourceAsUser = userMap[source.uniqueId]
    val viewerAsUser = userMap[(viewer as Player).uniqueId]
    val sourceData = if (overrideUserData == null) userDataMap[sourceAsUser] else overrideUserData

    if (sourceAsUser == null) {
        severe(console.asLangText("INTERNAL_USER_NOT_FOUND", sourceAsUser.toString()))
        throw NullPointerException(sourceAsUser)
    }
    if (sourceData == null) {
        severe(console.asLangText("INTERNAL_USERDATA_NOT_FOUND", sourceAsUser.toString()))
        throw NullPointerException(sourceData)
    }
    if (viewerAsUser == null) {
        severe(console.asLangText("INTERNAL_VIEWER_NOT_FOUND", sourceAsUser.toString()))
        throw NullPointerException(viewerAsUser)
    }

    val uuid = UUID.randomUUID()

    val reply = overrideMessage?.reply
    val internalPlaceholdered =
        if (overrideMessage?.reply != null) {
        val reply = overrideMessage.reply
        formatChat
            .replace("{reply}",formatReply)
            .replace("{sender}",formatSender)
            .replace("{format-message}",formatMessage)
    } else {
        formatChat
            .replace("{reply}","")
            .replace("{sender}",formatSender)
            .replace("{format-message}",formatMessage)
    }
    val input = internalPlaceholdered

    var content : Component

    if (subscribedContact is Group) {
        val member = subscribedContact.groupMember[sourceAsUser]
        content = mm.deserialize(input,
            Placeholder.unparsed("reply_sender", reply?.sender?.getRemarkOrNick(viewerAsUser).toString()),
            Placeholder.unparsed("reply_message", reply?.content.toString()),
            Placeholder.unparsed("group", subscribedContact.getRemarkOrNick(viewerAsUser).toString()),
            Placeholder.unparsed("level", member?.getTemperatureLevel().toString()),
            Placeholder.unparsed("title", member?.getTemperatureLevel().toString()), // TODO
            Placeholder.unparsed("senderplayername", sourceAsUser.getRemarkOrNick(viewerAsUser)),
        )
    } else {
        content = mm.deserialize(input,
            Placeholder.unparsed("reply_sender", reply?.sender?.getRemarkOrNick(viewerAsUser).toString()),
            Placeholder.unparsed("reply_message", reply?.content.toString()))
    }

    content.replaceText("<message>", message)



/*
*       <reply_sender>: <reply_message>
    sender: |
      [<group><gray>][<yellow>LV<level> <title><gray>] %luckperms_primary_group% <senderplayername>
    format-message: |
      <message>
* */

/*
    val internalPlaceholdered = formatChat
        .replace("{sender}",formatSender)
    val replyInternalPlaceholdered =
        if (overrideMessage?.reply != null) {
            formatChat
                .replace("{reply}",formatReply)
                .replace("{reply_sender}", reply.sender.getRemarkOrNick(viewerAsUser))
                .replace("{reply_message}", reply.content)
        } else {
            formatChat
                .replace("{reply}","")
        }
    val groupInternalPlaceholdered =
        if (overrideMessage?.reply != null) {
            val topGroup = sourceData.subscribeContact[0]
            formatChat
                .replace("{group}",topGroup.id.toString())
                .replace("{level}", "TODO")
                .replace("{title}", "TODO")
                .replace("{senderplayername}", topGroup.getRemarkOrNick(viewerAsUser))
        } else {
            formatChat
                .replace("{message}", )
        }

    message.replaceText("{message}", message)

    message.

*/

    return Message(uuid = overrideMessage?.uuid ?: uuid,
            sender = overrideMessage?.sender ?: sourceAsUser,
            type = overrideMessage?.type ?: MessageType.TEXT,
            meta = overrideMessage?.meta ?: LinkedHashMap(),
            content = overrideMessage?.content ?: content,
            sendedTime = overrideMessage?.sendedTime ?: Calendar.getInstance(),
            reply = overrideMessage?.reply,
            subscribeContact = subscribedContact)

}


fun sendMessage(source: Player, sourceDisplayName: Component, message: Component, viewer: Audience) {
    
}