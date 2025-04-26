package io.github.zzzyyylllty.chotenchat.function.message

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.Message
import io.github.zzzyyylllty.chotenchat.data.ReceiveMode.*
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUserWithoutDB
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import net.kyori.adventure.text.minimessage.MiniMessage
import java.util.UUID

/**
 * 本动作适用且只适用于玩家当前所在和发送到的后端服务器。
 * */
fun Message.call() {
    val message = this
    val mm = MiniMessage.miniMessage()
    val compStr = this.buildCompString()

    val receiveUsers = mutableListOf<User>()

    receiveContacts.forEach {
        if (it is User) receiveUsers.add(it) else if (it is Group) it.members.forEach {
            UUID.fromString(it.value.playerUUID).asUserWithoutDB() // TODO
        }
    }

    receiveUsers.forEach {
        val comp = this@call.buildMessage(compStr, it)
        it.sendMessage(mm.deserialize(comp))
    }
}

