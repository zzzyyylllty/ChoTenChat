package io.github.zzzyyylllty.melsydchat.logger

import ink.ptms.adyeshach.taboolib.common.platform.function.severe
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.console
import taboolib.common.platform.function.info
import taboolib.common.platform.function.warning
import taboolib.module.lang.asLangText


fun infoL(node: String,vararg args: kotlin.Any) {
    info(console.asLangText(node,args))
}
fun severeL(node: String,vararg args: kotlin.Any) {
    severe(console.asLangText(node,args))
}
fun warningL(node: String,vararg args: kotlin.Any) {
    warning(console.asLangText(node,args))
}