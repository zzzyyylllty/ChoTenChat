package io.github.zzzyyylllty.chotenchat.data


import io.github.zzzyyylllty.chotenchat.logger.infoL
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.Serializable
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.entity.Player
import java.util.UUID
import kotlin.collections.set


public interface Contact {
    val registryName: String
    val nickName: String?
    val longId: Long
    val idData: IdData

    fun getNickOrReg(): String {
        return nickName ?: registryName
    }
}

@Serializable
public data class User(
    override val registryName: String,
    override val nickName: String?,
    override val longId: Long,
    override val idData: IdData,
    val playerUUID: String,
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

@Serializable
public data class Group(
    override val registryName: String,
    override val nickName: String?,
    override val longId: Long,
    override val idData: IdData,
) : Contact {

}


