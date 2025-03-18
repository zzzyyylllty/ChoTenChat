package main.kotlin.io.github.zzzyyylllty.chotenchat

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.severeL
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.io.newFile
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import java.io.File
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
        saveCustomConfig()
        val file = newFile(getDataFolder(), "config.yml", create = true)
        config = Configuration.loadFromFile(file, Type.YAML) as ConfigFile
        val file2 = newFile(getDataFolder(), "placeholders.yml", create = true)
        placeHolderConfig = Configuration.loadFromFile(file2, Type.YAML) as ConfigFile
        plugin.config.reload()
        plugin.placeHolderConfig.reload()
    }


    fun saveCustomConfig() {
        infoL("INTERNAL_INFO_CREATING_CONFIG")
        try {
            YamlConfiguration.loadConfiguration((File(dataFolder, "config.yml"))).save(File(dataFolder, "config.yml"))
            YamlConfiguration.loadConfiguration((File(dataFolder, "placeholders.yml"))).save(File(dataFolder, "placeholders.yml"))
            infoL("INTERNAL_INFO_CREATED_CONFIG")
        } catch (e: Exception) {
            severeL("INTERNAL_SEVERE_CREATE_CONFIG_ERROR")
            e.printStackTrace()
        }
    }
    fun setupInstance() {
        plugin = this
    }
}