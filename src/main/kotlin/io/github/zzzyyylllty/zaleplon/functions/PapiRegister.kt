package io.github.zzzyyylllty.zaleplon.functions

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer


class PapiRegister : PlaceholderExpansion() {
    override fun getAuthor(): String {
        return "AkaCandyKAngel"
    }

    override fun getIdentifier(): String {
        return "embian"
    }

    override fun getVersion(): String {
        return "1.0.0"
    }

    override fun persist(): Boolean {
        return true // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    override fun onRequest(player: OfflinePlayer, params: String): String? {

        return "ERROR" // Placeholder is unknown
    }
}