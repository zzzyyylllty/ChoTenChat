package io.github.zzzyyylllty.melsydchat.data

import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import java.util.UUID
import kotlin.math.ceil
import kotlin.math.floor

interface Contact {
    /**
     * 联系人ID，可以是群号码/用户号码。
     * uuid 内部查询所用，不对外展示。
     *
     * @see User.id
     * @see Group.id
     * */
    val id: Long
    val uuid: UUID

    val typeId: String

    /**
     * 联系人备注。
     * 直接获取将永远为 null。请使用 [getRemark]
     * */
    val remark: String?

    /**
     * 联系人头像。ItemsAdder字符。
     * */
    val avatar: String?

    /**
     * 联系人名称。
     * */
    val name: String

    /**
     * 联系人备注。
     * */
    fun sendMessage(message: Message)

    fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: name
    }
    fun getRemark(finder: User): String? {
        return userDataMap[finder]?.userMeta["contact.${this.uuid}.remark"]
    }
}

interface Group : Contact {
    val groupName: String
    val groupMember: LinkedHashMap<User, GroupMember>

    /** 精华消息 */
    val essenceMessages: MutableList<EssenceMessage>

    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: groupName
    }
}

interface User : Contact {
    val playerName: String
    val nickname: String?

    /** 资料卡 */
    val profileCard: ProfileCard

    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(finder: User): String {
        return getRemark(finder) ?: nickname ?: playerName
    }
}

interface GroupMember : User {

    val groupName: String?
    val temperature: Long

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
    val qq: String,
    val oopz: String,
    val email: String,
    val discord: String,
    val github: String,
)

data class UserData(
    val subscribeContact: List<Contact>,
    val contactsList: List<Contact>,
    val topedContactsList: List<Contact>,
    val userMeta: LinkedHashMap<String, String>,
)

