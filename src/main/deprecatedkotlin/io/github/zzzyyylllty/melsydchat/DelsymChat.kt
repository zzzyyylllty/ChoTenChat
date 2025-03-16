package main.kotlin.io.github.zzzyyylllty.zaleplon

import ink.ptms.adyeshach.taboolib.module.nms.Exchanges.ExchangePlugin.getServer
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submitAsync
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.module.lang.asLangText
import taboolib.platform.util.bukkitPlugin
import java.util.*

object DelsymChat : Plugin() {

    lateinit var plugin: DelsymChat
    lateinit var userDataMap: LinkedHashMap<User, UserData>
    lateinit var userMap: LinkedHashMap<UUID, User>
    lateinit var loadedGroupList: LinkedHashMap<UUID, Group>
    var devMode = true
    var console = console()

    @Config("config.yml")
    lateinit var config: ConfigFile

    override fun onEnable() {
        info("Successfully running ExamplePlugin!")
        submitAsync {
            Bukkit.broadcastMessage("1111")
        }
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

}