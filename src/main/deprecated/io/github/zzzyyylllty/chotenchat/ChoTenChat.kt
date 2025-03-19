package main.kotlin.io.github.zzzyyylllty.chotenchat

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.severeL
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.io.newFile
import taboolib.common.platform.Awake
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.warning
import taboolib.common.platform.function.severe
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import java.io.File
import java.util.*

object ChoTenChat : Plugin() {

    lateinit var plugin: ChoTenChat
    val host = config.getHost("database")
    val dataSource by lazy { host.createDataSource() }
    var dataFolder = nativeDataFolder()
    var userDataMap = LinkedHashMap<Long, UserData>() // KID, UserData...
    var userMap = LinkedHashMap<Long, User>() // KID, UserData...
    var playerAsUserMap = LinkedHashMap<UUID, Long>() // PlayerUUID, KID
    var loadedGroupMap = LinkedHashMap<Long, Group>()
    var devMode = true
    var console = console()

    @Config("config.yml")
    lateinit var config: Configuration

    @Config("placeholders.yml")
    lateinit var placeHolderConfig: Configuration

    @Awake(LifeCycle.ENABLE)
    override fun onEnable() {
        warning("ChoTenChat now starting.")
        setupInstance()
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

    fun reloadCustomConfig() {
        createCustomConfig()
        plugin.config.reload()
        plugin.placeHolderConfig.reload()
    }


    fun createCustomConfig() {
        infoL("INTERNAL_INFO_CREATING_CONFIG")
        try {
            placeHolderConfig = Configuration.loadFromFile(newFile(getDataFolder(), "placeholders.yml", create = true), Type.YAML)
            config = Configuration.loadFromFile(newFile(getDataFolder(), "config.yml", create = true), Type.YAML)
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