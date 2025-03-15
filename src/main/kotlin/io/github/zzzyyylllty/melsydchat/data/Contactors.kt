package io.github.zzzyyylllty.melsydchat.data

interface Contact {
    /**
     * 联系人ID，可以是群号码/用户号码。
     *
     * @see User.id
     * @see Group.id
     * */
    val id: Long
    val typeId: String

    /**
     * 联系人备注。
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

    fun getRemarkOrNick(): String {
        return remark ?: name
    }
}

interface Group : Contact {
    val groupName: String

    /** 精华消息 */
    val essenceMessages: MutableList<EssenceMessage>

    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(): String {
        return remark ?: groupName
    }
}

interface User : Contact {
    val playerName: String
    val nickname: String?

    /** 资料卡 */
    val profileCard: ProfileCard

    /** 获取展示的用户昵称 */
    override fun getRemarkOrNick(): String {
        return remark ?: nickname ?: playerName
    }
}

interface GroupMember : User {

    val groupName: String?

    /** 获取展示的群组昵称 */
    override fun getRemarkOrNick(): String {
        return remark ?: groupName ?: nickname ?: playerName
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
)