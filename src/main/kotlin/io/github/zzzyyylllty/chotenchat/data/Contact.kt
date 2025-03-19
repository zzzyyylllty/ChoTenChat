package io.github.zzzyyylllty.chotenchat.data

import java.util.UUID

public interface Contact {
    val registryName: String
    val nickName: String?
    val longId: String
    val idData: IdData

    fun getNickOrReg(): String {
        return nickName ?: registryName
    }
}

public data class User(
    override val registryName: String,
    override val nickName: String?,
    override val longId: String,
    override val idData: IdData,
    val playerUUID: UUID,
    val userData: UserData
) : Contact {

}

public data class Group(
    override val registryName: String,
    override val nickName: String?,
    override val longId: String,
    override val idData: IdData
) : Contact {

}