package io.github.zzzyyylllty.melsydchat.data

import com.beust.klaxon.Json
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

/**
 * 玩家数据，用于保存玩家设置等
 *
 * */
interface ContactData {
    val contactSettings: LinkedHashMap<String, ContactSetting>

    fun getReceiveMode(kid: String): ReceiveMode {
        return contactSettings[kid]?.receiveMode ?: ReceiveMode.ALWAYS
    }
    fun getRemarkOrNull(kid: String): String? {
        return contactSettings[kid]?.remark
    }
    fun getBlackListedStat(kid: String): Boolean {
        return contactSettings[kid]?.isBlackListed ?: false
    }
}

data class GroupData(
    override val contactSettings: LinkedHashMap<String, ContactSetting>
): ContactData {

}

data class UserData(
    @Json("contactsettings")
    override val contactSettings: LinkedHashMap<String, ContactSetting>,
    @Json("subscribedcontact")
    val subscribedContact : String

): ContactData {
}

data class ContactSetting(
    @Json("receivemode")
    val receiveMode: ReceiveMode,
    @Json("remark")
    val remark: String,
    @Json("isblacklisted")
    val isBlackListed: Boolean,

    )

/**
 * 消息接收模式。适用于所有联系人
 * [SUBSCRIBE] 特别关心，在其它联系人处也可收到(会更醒目)
 * [ALWAYS] 总是提醒，在其它联系人处也可收到(默认)
 * [NOTE] 提醒，在其它联系人处仅提及时收到
 * [HIDDEN] 隐藏，在其它联系人处不可接收
 * [SELF] 这是一条自己的消息
 * */
enum class ReceiveMode {
    SUBSCRIBE,
    ALWAYS,
    NOTE,
    HIDDEN,
    SELF,
}