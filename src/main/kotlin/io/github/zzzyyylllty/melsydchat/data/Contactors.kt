package io.github.zzzyyylllty.melsydchat.data

import java.util.Calendar
import java.util.UUID
import kotlin.math.floor

interface Contact {
    val id: Long
    val uuid: UUID
    val name: String
    val avatar: String?
    fun sendMessage(message: Message)
}

class Group(
    override val id: Long,
    override val uuid: UUID,
    override val name: String,
    override val avatar: String,
    val groupMember: LinkedHashMap<User, MemberData>,

    ) : Contact {
    override fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }

}

open class User(
    override val id: Long,
    override val uuid: UUID,
    override val name: String,
    override val avatar: String?,
    val playerUUID: UUID,
    open var nickName: String?,
): Contact {
    override fun sendMessage(message: Message) {
        //Bukkit.getPlayer(playerUUID).sendMessage(PatchedMessage(
        //    message,
        //)
        //)
    }

}

class Friend(
    val friendUser: User,
    id: Long,
    uuid: UUID,
    name: String,
    avatar: String,
    playerUUID: UUID,
    nickName: String?,
): User(id, uuid, name, avatar, playerUUID, nickName)


data class MemberData(
    val titleSelection: TitleType,
    val temperature: Long,
    val isMuted: Boolean,
    val muteTimeEnd: Calendar,
    val groupName: String?
) {

    fun getTemperatureLevel(): Long {
        return floor(temperature/(temperature/100+10.0)).toLong()
    }
}

data class UserData(
    val subscribeContact: Contact,
    val contactorSetting: LinkedHashMap<UUID, ContactorSetting>
)

enum class TitleType {
    TEMPERATURE,
    SPECIAL,
    PERMISSION,
}