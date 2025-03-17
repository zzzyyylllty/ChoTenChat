package io.github.zzzyyylllty.chotenchat.function.boot
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.placeHolderConfig
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.plugin
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.reloadCustomConfig
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import taboolib.common.platform.function.warning

@Awake(LifeCycle.ENABLE)
fun initializeData() {
    ChoTenChat.setupInstance()
    reloadCustomConfig()
    info("ChoTenChat now running.")
    plugin.placeHolderConfig.reload()
    plugin.config.reload()
    warning("$config $placeHolderConfig")
}
