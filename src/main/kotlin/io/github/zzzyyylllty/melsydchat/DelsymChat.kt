package main.kotlin.io.github.zzzyyylllty.zaleplon

import ink.ptms.adyeshach.taboolib.module.nms.Exchanges.ExchangePlugin.getServer
import io.github.zzzyyylllty.melsydchat.data.ContactIDContainer
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.function.boot.initializeData
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.ProxyPlayer
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
    lateinit var userDataMap: LinkedHashMap<Long, UserData>
    lateinit var userMap: LinkedHashMap<Long, User>
    lateinit var playerAsUserMap: LinkedHashMap<UUID, Long>
    lateinit var loadedGroupMap: LinkedHashMap<Long, Group>
    var devMode = true
    var console = console()

    @Config("config.yml")
    lateinit var config: ConfigFile
    @Config("placeholders.yml")
    lateinit var placeholderconfig: ConfigFile

    override fun onEnable() {
        info("Successfully running ExamplePlugin!")
        initializeData()
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

}