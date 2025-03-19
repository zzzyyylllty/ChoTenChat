package io.github.zzzyyylllty.chotenchat.data



/**
 * 以下被界定为靓号
 * AAA，AAAA 111xxxxxx
 * ABAB，ABABAB 121212xx
 * 连字 12345xxxx
 * 1900-2050年之间的完整日期，排除敏感日期 20000530xx
 *
 */

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

enum class FancyAccountType {
    NORMAL,
    FANCY,
    GOLD,
    BLACK_GOLD,
    ADMINISTRATOR,
}

data class UserData(
    val subscribeContact: Contact,
    val contactSettings: LinkedHashMap<Contact, ContactSetting>,
)

data class ContactSetting(
    val receiveMode: ReceiveMode = ReceiveMode.ALWAYS,
)
enum class ReceiveMode {
    SUBSCRIBE,
    ALWAYS,
    NOTE,
    HIDDEN,
    SELF,
}