package io.github.zzzyyylllty.chotenchat.function.boot
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.plugin
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.reloadCustomConfig
import taboolib.common.LifeCycle
import taboolib.common.io.newFile
import taboolib.common.platform.Awake
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.common.platform.function.warning
import taboolib.module.configuration.ConfigFile
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type

@Awake(LifeCycle.ENABLE)
fun initializeData() {
    val file = newFile(getDataFolder(), "config.yml", create = true)
    config = Configuration.loadFromFile(file, Type.YAML) as ConfigFile

    val file2 = newFile(getDataFolder(), "placeholders.yml", create = true)
    placeHolderConfig = Configuration.loadFromFile(file2, Type.YAML) as ConfigFile

    severe("${placeHolderConfig.getValues(true)}")

    ChoTenChat.setupInstance()
    reloadCustomConfig()
    info("ChoTenChat now running.")
    plugin.placeHolderConfig.reload()
    plugin.config.reload()

}
