package io.github.zzzyyylllty.chotenchat.function.boot
import io.github.zzzyyylllty.chotenchat.database.SQLDataBase
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.reloadCustomConfig
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import taboolib.common.platform.function.severe
import taboolib.common.platform.function.submit
import taboolib.common.platform.function.submitAsync

@Awake(LifeCycle.ENABLE)
fun initializeData() {
    reloadCustomConfig()
}

@Awake(LifeCycle.DISABLE)
fun saveData() {
        val sql = SQLDataBase()
        for (value in loadedGroupMap.values) {
            sql.saveInDatabase(value)
        }
        for (value in userMap.values) {
            sql.saveInDatabase(value)
        }
}
