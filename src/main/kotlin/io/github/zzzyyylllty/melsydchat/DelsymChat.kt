package main.kotlin.io.github.zzzyyylllty.zaleplon

import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.function.boot.initializeData
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submitAsync
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.lang.asLangText
import taboolib.platform.util.bukkitPlugin
import java.util.*

object DelsymChat : Plugin() {

    lateinit var plugin: DelsymChat
    var dataFolder = nativeDataFolder()
    var userDataMap = LinkedHashMap<String, UserData>() // KID, UserData...
    var userMap = LinkedHashMap<String, User>() // KID, UserData...
    var playerAsUserMap = LinkedHashMap<UUID, String>() // PlayerUUID, KID
    var loadedGroupMap = LinkedHashMap<String, Group>()
    var devMode = true
    var console = console()

    @Config("config.yml")
    var config: ConfigFile? = null

    @Config("placeholders.yml")
    var placeholderconfig: ConfigFile? = null

    override fun onEnable() {
        reloadCustomConfig()
        info("Successfully running ExamplePlugin!")
        initializeData()
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

    fun reloadCustomConfig() {
        config?.reload()
        placeholderconfig?.reload()
        dataFolder = getDataFolder()
    }
}