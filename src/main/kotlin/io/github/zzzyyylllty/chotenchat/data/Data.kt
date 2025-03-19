package io.github.zzzyyylllty.chotenchat.data



/**
 * 以下被界定为靓号
 * AAA，AAAA 111xxxxxx
 * ABAB，ABABAB 121212xx
 * 连字 12345xxxx
 * 1900-2050年之间的完整日期，排除敏感日期 20000530xx
 *
 */

@kotlinx.serialization.Serializable
data class IdData(
    val fancyAccountType: FancyAccountType,
    val fancyAccountValue: Int,
)

/**
[NORMAL], 普通号
[FANCY], 普通号
[GOLD], 黄金号
[BLACK_GOLD], 黑金号
[ADMINISTRATOR] 管理员号
 */

@kotlinx.serialization.Serializable
enum class FancyAccountType {
    NORMAL,
    FANCY,
    GOLD,
    BLACK_GOLD,
    ADMINISTRATOR,
}

@kotlinx.serialization.Serializable
data class UserData(
    val subscribeContact: Contact?,
    val contactSettings: LinkedHashMap<Contact, ContactSetting>,
)

@kotlinx.serialization.Serializable
data class ContactSetting(
    val receiveMode: ReceiveMode = ReceiveMode.ALWAYS,
)

@kotlinx.serialization.Serializable
enum class ReceiveMode {
    SUBSCRIBE,
    ALWAYS,
    NOTE,
    HIDDEN,
    SELF,
}