package io.github.zzzyyylllty.chotenchat.data

import com.beust.klaxon.Json
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.*
import io.github.zzzyyylllty.chotenchat.data.TitleSelection.*
import io.github.zzzyyylllty.chotenchat.logger.warningL
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.function.warning
import java.util.UUID
import kotlin.collections.iterator
import kotlin.math.floor

/**
 * 联系人接口
 *
 * [registryName] - 注册名称。
 * [nickName] - 昵称。
 * [numberId] - 数字ID。
 * */
interface Contact {
    var registryName: String
    var nickName: String?
    var fullId: FullID

    fun getName(): String {
        return nickName ?: registryName
    }
    fun getShortName(): String {
        var name = (nickName ?: registryName)
        return if (name.length >= 5) name.substring(0, 5) else name
    }
    fun getId(): Long {
        return fullId.UIDData.numberUID
    }
    fun getUId(): UIDData {
        return fullId.UIDData
    }
    fun getIdColor(): String {
        return when (fullId.UIDData.fancyAccountType) {
            NORMAL -> "<#ff0000>"
            FANCY -> "<gradient:#ffaa99:#ff5500>"
            GOLD -> "<gradient:#eeee99:#ffcc00>"
            BLACK_GOLD -> "<gradient:#ffff66:#ffcc00:#888877:#555566>"
            ADMINISTRATOR -> "<gradient:#ff9999:#ff3333:#cc0000>"
        }
    }
}

data class User(
    override var registryName: String,
    override var nickName: String?,
    override var fullId: FullID,
    var uuid: UUID

    ) : Contact {
    fun asPlayer(): Player? {
        return Bukkit.getPlayer(uuid)
    }
    fun asUserData() : UserData? {
        return userDataMap[fullId.UIDData.numberUID] // 接收者的设置
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
    override var registryName: String,
    @Json(name = "nickname")
    override var nickName: String?,
    @Json(name = "fullid")
    override var fullId: FullID,
    var members: LinkedHashMap<Long, Member>,
    var temperatureTitleLevel: LinkedHashMap<Int, String>,

    ) : Contact {

    fun getTemperatureTitle(tempLevel: Int): String {
        if (temperatureTitleLevel.get(0) == null) {
            warning("The First Temperature Level Not Set.Returning null.")
        }
        var lastTitle: String = temperatureTitleLevel[0].toString() // 上一个头衔
        for (level in temperatureTitleLevel) {
            var requiredLevel = level.key
            if (requiredLevel > tempLevel) { // 如果这个头衔的要求超过了当前等级，即应用头衔
                return lastTitle
            }
        }
        return lastTitle
    }
}

data class Member(
    var group: Group,
    var nickName: String,
    var temperature: Long,
    var specialTitle: String?,
    var groupPermission: GroupPermission,
    var titleSelection: TitleSelection
) {
    fun isTrusted(): Boolean {
        return (groupPermission == GroupPermission.TRUSTED || groupPermission == GroupPermission.ADMINISTRATOR || groupPermission == GroupPermission.BUREAUCRAT || groupPermission == GroupPermission.OWNER)
    }
    fun isAdmin(): Boolean {
        return (groupPermission == GroupPermission.ADMINISTRATOR || groupPermission == GroupPermission.BUREAUCRAT || groupPermission == GroupPermission.OWNER)
    }
    fun isBureaucrat(): Boolean {
        return (groupPermission == GroupPermission.BUREAUCRAT || groupPermission == GroupPermission.OWNER)
    }
    fun isOwner(): Boolean {
        return (groupPermission == GroupPermission.OWNER)
    }
    fun getTempLevel(): Int {
        return floor(temperature/((temperature/1000)+10.0)).toInt()
    }
    fun getTempvarue(): Long {
        return temperature
    }
    fun getTitle(): String {
        return when (titleSelection) {
            TEMPERATURE -> group.getTemperatureTitle(getTempLevel())
            PERMISSION -> groupPermission.name
            UNIQUE -> specialTitle
        }.toString()
    }
}

data class FullID(
    var type: ContactType,
    var UIDData: UIDData,
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

enum class GroupPermission {
    MEMBER,
    TRUSTED,
    ADMINISTRATOR,
    BUREAUCRAT,
    OWNER
}

enum class TitleSelection {
    TEMPERATURE,
    PERMISSION,
    UNIQUE
}

fun getGroupOrFail(long: Long) : Group {
    return loadedGroupMap[long] ?: throw NullPointerException()
}