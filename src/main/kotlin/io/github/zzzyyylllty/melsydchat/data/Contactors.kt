package io.github.zzzyyylllty.melsydchat.data

import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import org.bukkit.event.Event
import java.util.UUID
import kotlin.math.floor

interface Contact {
    val id: Long
    val uuid: UUID
    val name: String
    val avatar: String
}

class Group(
    override val id: Long,
    override val uuid: UUID,
    override val name: String,
    override val avatar: String,
    val groupMember: LinkedHashMap<User, MemberData>,
    val essenceMessages: MutableList<EssenceMessage>,

) : Contact {

}

class User(
    override val id: Long,
    override val uuid: UUID,
    override val name: String,
    override val avatar: String,
    var nickName: String?
): Contact {


}

data class MemberData(
    val titleSelection: TitleType,
    val temperature: Long,
    val groupName: String?
) {

    fun getTemperatureLevel(): Long {
        return floor(temperature/(temperature/100+10.0)).toLong()
    }
}

enum class TitleType {
    TEMPERATURE,
    SPECIAL,
    PERMISSION,
}