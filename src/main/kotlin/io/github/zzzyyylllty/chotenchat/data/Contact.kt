package io.github.zzzyyylllty.chotenchat.data


import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.Serializable
import java.util.UUID


public interface Contact {
    val registryName: String
    val nickName: String?
    val longId: String
    val idData: IdData
    var internalId: Long

    fun getNickOrReg(): String {
        return nickName ?: registryName
    }
}

@Serializable
public data class User(
    override val registryName: String,
    override val nickName: String?,
    override val longId: String,
    override val idData: IdData,
    override var internalId: Long,
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
    override val longId: String,
    override val idData: IdData,
    override var internalId: Long,
) : Contact {

}

