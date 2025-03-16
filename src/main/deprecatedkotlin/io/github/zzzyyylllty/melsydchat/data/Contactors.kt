package io.github.zzzyyylllty.melsydchat.data

import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import org.bukkit.event.Event
import java.util.UUID
import kotlin.math.floor

abstract class Contact(
    /**
     * 联系人ID，可以是群号码/用户号码。
     * uuid 内部查询所用，不对外展示。
     *
     * @see User.id
     * @see Group.id
     * */
    val id: Long,
    val uuid: UUID,

    val typeId: ContactType,

    /**
     * 联系人备注。
     * 直接获取将永远为 null。请使用 [getRemark]
     * */
    val remark: String?,
    val name: String,
    /**
     * 联系人头像。ItemsAdder字符。
     * */
    val avatar: String?
) {

    open fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: name

    }
    fun getRemark(finder: User): String? {
        val getedRemark = userDataMap[finder]
        return if (getedRemark == null) {
            null
        } else {
            getedRemark.userMeta["contact.${uuid}.remark"]
        }
    }
}

class Group(
    val groupName: String,
    val groupMember: LinkedHashMap<User, GroupMember>,

    /** 精华消息 */
    val essenceMessages: MutableList<EssenceMessage>,

id: Long, uuid: UUID, typeId: ContactType, remark: String?, name: String, avatar: String?,
    ) : Contact(id, uuid, typeId, remark, name, avatar) {
    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: groupName
    }
}

open class User(
    val playerName: String,
    val nickname: String?,

    /** 资料卡 */
    val profileCard: ProfileCard,
    id: Long, uuid: UUID, typeId: ContactType, remark: String?, name: String, avatar: String?,

    ) : Contact(id, uuid, typeId, remark, name, avatar) {
    fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }

    fun data(): UserData? {
        return userDataMap[this]
    }

    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: nickname ?: playerName
    }
}

class GroupMember(
    playerName: String,
    nickname: String?,
    profileCard: ProfileCard,
    id: Long,
    uuid: UUID,
    typeId: ContactType = ContactType.STANDARD,
    remark: String?,
    avatar: String?,
    name: String,
    val temperature: Long,
    val groupName: String?
) : User(playerName, nickname, profileCard, id, uuid, typeId, remark, name, avatar) {

    /** 获取展示的群组昵称 */
    override fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: groupName ?: nickname ?: playerName
    }

    fun getTemperatureLevel(): Long {
        return floor(temperature/(temperature/100+10.0)).toLong()
    }
}

/**
 * - [lore] 个性签名
 * - [otherAccount] 其他平台帐号
 *
 *
 * */
data class ProfileCard(
    val lore: String,
    val otherAccount: OtherAccount,
    val tags: List<String>,
)

data class OtherAccount(
    val qq: String?,
    val oopz: String?,
    val email: String?,
    val discord: String?,
    val github: String?,
)

data class UserData(
    val subscribeContact: Contact,
    val contactsList: List<Contact>,
    val topedContactsList: List<Contact>,
    val userMeta: LinkedHashMap<String, String>,
)

enum class ContactType {
    BOT,
    STANDARD,
    GROUP
}
