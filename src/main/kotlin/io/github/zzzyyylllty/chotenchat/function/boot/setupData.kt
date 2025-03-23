package io.github.zzzyyylllty.chotenchat.function.boot
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.reloadCustomConfig
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe

@Awake(LifeCycle.ENABLE)
fun initializeData() {
    reloadCustomConfig()

    info("ChoTenChat now running.")

}
