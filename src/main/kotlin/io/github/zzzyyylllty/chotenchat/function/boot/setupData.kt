package io.github.zzzyyylllty.chotenchat.function.boot
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.reloadCustomConfig
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info

@Awake(LifeCycle.ENABLE)
fun initializeData() {
    ChoTenChat.setupInstance()
    reloadCustomConfig()
    info("ChoTenChat now running.")

}
