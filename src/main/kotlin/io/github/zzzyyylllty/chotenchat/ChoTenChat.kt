package main.kotlin.io.github.zzzyyylllty.chotenchat

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import taboolib.common.io.newFile
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import java.util.*

object ChoTenChat : Plugin() {

    lateinit var plugin: ChoTenChat
    var dataFolder = nativeDataFolder()
    var userDataMap= LinkedHashMap<String, UserData>() // KID, UserData...
    var userMap = LinkedHashMap<String, User>() // KID, UserData...
    var playerAsUserMap = LinkedHashMap<UUID, String>() // PlayerUUID, KID
    var loadedGroupMap = LinkedHashMap<String, Group>()
    var devMode = true
    var console = console()

    @Config("config.yml")
    lateinit var config: ConfigFile

    @Config("placeholders.yml")
    lateinit var placeHolderConfig: ConfigFile

    override fun onEnable() {
        severe("ChoTenChat now starting.")
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

    fun reloadCustomConfig() {
        plugin.config.reload()
        plugin.placeHolderConfig.reload()
    }
    fun setupInstance() {
        plugin = this
    }
}