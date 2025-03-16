package io.github.zzzyyylllty.melsydchat.data


data class ContactorSetting(
    val remark: String,
    val receiveMode: MessageReceiveMode,
) {

}

enum class MessageReceiveMode {
    MENTION,
    ALWAYS,
    HIDDEN,
}