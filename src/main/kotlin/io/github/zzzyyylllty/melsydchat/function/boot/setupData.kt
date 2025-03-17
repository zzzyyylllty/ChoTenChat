package io.github.zzzyyylllty.melsydchat.function.boot
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.config
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import org.bukkit.Bukkit
import taboolib.common.io.newFile
import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.ConfigFile
import java.util.UUID

fun initializeData() {
    userDataMap = linkedMapOf()
    userMap = linkedMapOf()
}
