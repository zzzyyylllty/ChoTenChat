package io.github.zzzyyylllty.zaleplon.functions

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer

fun solvePapi(s: String, p: OfflinePlayer?): String {
    return PlaceholderAPI.setPlaceholders(p, s)
}