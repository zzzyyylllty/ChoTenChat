package io.github.zzzyyylllty.zaleplon.functions

import io.github.zzzyyylllty.zaleplon.functions.debugger.devLog
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer

fun solvePapi(s: String, p: OfflinePlayer?): String {
    devLog("Solving Papi: $s with player ${p?.name}")
    return PlaceholderAPI.setPlaceholders(p, s)
}