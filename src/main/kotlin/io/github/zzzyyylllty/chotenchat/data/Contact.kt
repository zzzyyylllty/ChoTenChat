package io.github.zzzyyylllty.chotenchat.data

import com.beust.klaxon.Json
import io.github.zzzyyylllty.chotenchat.logger.warningL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID
import kotlin.math.min

/**
 * 联系人接口
 *
 * [registryName] - 注册名称。
 * [nickName] - 昵称。
 * [numberId] - 数字ID。
 * */
interface Contact {
    val registryName: String
    val nickName: String?
    val fullId: FullID

    fun getName(): String {
        return nickName ?: registryName
    }
    fun getShortName(): String {
        val name = (nickName ?: registryName)
        return if (name.length >= 5) name.substring(0, 5) else name
    }
    fun getId(): String {
        return fullId.UIDData.numberUID
    }
    fun getUId(): UIDData {
        return fullId.UIDData
    }
}

data class User(
    @Json(name = "regname")
    override val registryName: String,
    @Json(name = "nickname")
    override val nickName: String?,
    @Json(name = "fullid")
    override val fullId: FullID,
    @Json(name = "playeruuid")
    val uuid: UUID

    ) : Contact {
    fun asPlayer(): Player? {
        return Bukkit.getPlayer(uuid)
    }
    fun asUserData() : UserData? {
        return userDataMap[fullId.getKID()] // 接收者的设置
    }

    fun sendChoTenMessage(message: String) {

    }
}

fun Player.asUser(): User? {
    return userMap[playerAsUserMap[this.uniqueId]] ?: run {
        warningL("INTERNAL_WARNING_UNABLE_TO_FIND_USER_BY_PLAYER", this.player?.name ?:"(null)", this.uniqueId)
        return null
    }
}

data class Group(
    @Json(name = "regname")
    override val registryName: String,
    @Json(name = "nickname")
    override val nickName: String?,
    @Json(name = "fullid")
    override val fullId: FullID,

    ) : Contact {
}

data class FullID(
    val type: ContactType,
    val UIDData: UIDData,
) {
    override fun toString(): String {
        return "${type.name}-${UIDData.numberUID}"
    }
    fun getKID(): String {
        return "${type.name}-${UIDData.numberUID}"
    }
}

enum class ContactType{
    USER,
    GROUP,
}