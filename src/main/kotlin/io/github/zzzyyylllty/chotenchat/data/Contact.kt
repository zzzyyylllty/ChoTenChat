package io.github.zzzyyylllty.chotenchat.data


import com.beust.klaxon.Klaxon
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.ADMINISTRATOR
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.BLACK_GOLD
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.FANCY
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.GOLD
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType.NORMAL
import io.github.zzzyyylllty.chotenchat.data.TitleSelection.*
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import taboolib.module.lang.asLangText
import java.util.UUID
import kotlin.math.floor


public interface Contact {
    val registryName: String
    val nickName: String?
    val longId: Long
    val idData: IdData

    fun getNickOrReg(): String {
        return nickName ?: registryName
    }
    fun getGroupOrNickOrReg(member: Member?): String {
        return member?.groupName ?: nickName ?: registryName
    }
    fun getShortName(): String {
        var name = (nickName ?: registryName)
        return if (name.length >= 5) name.substring(0, 5) else name
    }
    fun getIdColor(): String {
        return when (idData.fancyAccountType) {
            NORMAL -> "<#ff0000>"
            FANCY -> "<gradient:#ffaa99:#ff5500>"
            GOLD -> "<gradient:#eeee99:#ffcc00>"
            BLACK_GOLD -> "<gradient:#ffff66:#ffcc00:#888877:#555566>"
            ADMINISTRATOR -> "<gradient:#ff9999:#ff3333:#cc0000>"
        }
    }
    fun getJson(): String {
        return Klaxon().toJsonString(this)
    }
}

public data class User(
    override val registryName: String,
    override val nickName: String?,
    override val longId: Long,
    override val idData: IdData,
    val playerUUID: String,
    val playerName: String,
    val data: UserData,
) : Contact {
    @Deprecated("direct use User.data instead. From v0.3 user & userdata is merged.")
    fun asUserData(): UserData {
        return data
    }

    fun getUUID(): UUID? {
        return UUID.fromString(playerUUID)
    }
}

public data class Group(
    override val registryName: String,
    override val nickName: String?,
    override val longId: Long,
    override val idData: IdData,
    var members: LinkedHashMap<Long, Member>,
    var temperatureTitleLevel: LinkedHashMap<Int, String>
) : Contact {
    fun getTemperatureTitle(tempLevel: Int): String {
        if (temperatureTitleLevel.get(0) == null) {
            return console.asLangText("STRING_NONE")
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

public data class Member(
    val groupName: String?,
    val longId: Long,
    val group: Long,
    val playerUUID: String,
    val playerName: String,
    val temperature: Long,
    val specialTitle: String?,
    var groupPermission: GroupPermission,
    var titleSelection: TitleSelection,
    var titleTheme: String,
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
    fun getTempvalue(): Long {
        return temperature
    }
    fun getTitle(g: Group): String {
        return when (titleSelection) {
            TEMPERATURE -> g.getTemperatureTitle(getTempLevel())
            PERMISSION -> groupPermission.name
            SPECIAL -> specialTitle
        }.toString()
    }
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
    SPECIAL
}
