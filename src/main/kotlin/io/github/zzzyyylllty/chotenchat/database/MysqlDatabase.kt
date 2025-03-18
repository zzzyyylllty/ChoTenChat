package io.github.zzzyyylllty.chotenchat.database

import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import taboolib.module.database.getHost
import kotlin.getValue

/*
*     lateinit var userDataMap: LinkedHashMap<String, UserData> // KID, UserData...
    lateinit var userMap: LinkedHashMap<String, User> // KID, UserData...
    lateinit var playerAsUserMap: LinkedHashMap<UUID, String> // PlayerUUID, KID
    lateinit var loadedGroupMap: LinkedHashMap<String, Group>
* */

/*
class MysqlDatabase {
    val host = config!!.getHost("database")
    val dataSource by lazy { host.createDataSource() }
    val table = Table("user", host) {
        add { id() }
        add("user") {
            type(ColumnTypeSQL.VARCHAR, 36) {
                options(ColumnOptionSQL.KEY)
            }
        }
        add("key") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                // 创建索引
                options(ColumnOptionSQL.KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.VARCHAR, 128)
        }
    }
    init {
        table.createTable(dataSource)
    }
}*/