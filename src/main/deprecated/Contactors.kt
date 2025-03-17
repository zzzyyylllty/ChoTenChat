package io.github.zzzyyylllty.melsydchat.data

import io.github.zzzyyylllty.melsydchat.data.ContactType.GROUP
import io.github.zzzyyylllty.melsydchat.data.ContactType.USER
import io.github.zzzyyylllty.melsydchat.function.asUserData
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.Calendar
import java.util.UUID
import kotlin.math.floor

interface Contact {
    val idData: ContactIDContainer
    val name: String
    val avatar: String?
    fun sendMessage(message: Message)
}

class Group(
    override val idData: ContactIDContainer,
    override val name: String,
    override val avatar: String?,
    val groupMember: LinkedHashMap<Long, MemberData>,

    ) : Contact {
    override fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }

}

open class User(
    override val idData: ContactIDContainer,
    override val name: String,
    override val avatar: String?,
    val playerUUID: UUID,
    open var nickName: String?,
): Contact {
    override fun sendMessage(message: Message) {
    }

    fun getMessageReceiveMode(contact: Contact): MessageReceiveMode {
        return this.asUserData()?.contactorSetting?.get(contact.idData)?.receiveMode ?: MessageReceiveMode.ALWAYS
    }
    fun asPlayer(): Player? {
        return Bukkit.getPlayer(playerUUID)
    }
    fun getNickOrName(): Player? {
        return Bukkit.getPlayer(playerUUID)
    }

}

class Friend(
    val friendUser: User,
    idData: ContactIDContainer,
    name: String,
    avatar: String,
    playerUUID: UUID,
    nickName: String?,
): User(idData, name, avatar, playerUUID, nickName)


data class MemberData(
    val titleSelection: TitleType,
    val temperature: Long,
    val isMuted: Boolean,
    val muteTimeEnd: Calendar?,
    val groupName: String?
) {

    fun getTemperatureLevel(): Long {
        return floor(temperature/(temperature/100+10.0)).toLong()
    }
}

data class UserData(
    val subscribeContact: ContactIDContainer,
    val contactorSetting: LinkedHashMap<ContactIDContainer, ContactorSetting>
)

enum class TitleType {
    TEMPERATURE,
    SPECIAL,
    PERMISSION,
}

data class ContactIDContainer(
    val id: Long,
    val type: ContactType,
) {
    fun asContact(type: ContactType): Contact? {
        val contactID = id
        return when (type) {
            USER -> userMap[contactID]
            GROUP -> loadedGroupMap[contactID]
        }
    }
}

enum class ContactType {
    GROUP,
    USER
}