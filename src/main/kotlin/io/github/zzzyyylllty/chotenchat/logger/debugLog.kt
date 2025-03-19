package io.github.zzzyyylllty.chotenchat.logger

import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.console
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.devMode
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.common.platform.function.warning
import taboolib.module.lang.asLangText


fun debugLog(msg: String) {
    if (devMode) warning("[DEBUG] $msg")
}