package io.github.zzzyyylllty.zaleplon.functions.debugger

import io.github.zzzyyylllty.zaleplon.Zaleplon
import taboolib.common.platform.function.info

fun devLog(s: String) {
    if (Zaleplon.devMode) info("[DEVMODE] $s")
}

fun devMode(b: Boolean) {
    Zaleplon.devMode = b
}