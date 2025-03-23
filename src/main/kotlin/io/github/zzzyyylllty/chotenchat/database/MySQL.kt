package io.github.zzzyyylllty.chotenchat.database

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.dataSource
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.host
import org.bukkit.entity.Player
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Database
import taboolib.module.database.Table
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync
import taboolib.common.util.unsafeLazy
import taboolib.common5.Coerce
import taboolib.module.database.getHost
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import javax.sql.DataSource

public open class SQLDataBase {

    val userTable = Table("user_table", host) {
        add { id() }
        add("uuid") { // Player UUID
            type(ColumnTypeSQL.VARCHAR, 36) {
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("id") { // LongID
            type(ColumnTypeSQL.VARCHAR, 64) {
                // 创建索引
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.JSON, 128)
        }
    }

    val groupTable = Table("group_table", host) {
        add { id() }
        add("id") { // LongID
            type(ColumnTypeSQL.VARCHAR, 64) {
                // 创建索引
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.JSON, 128)
        }
    }

    public fun saveInDatabase(user: User) {
        val json = Klaxon().toJsonString(user)
        userTable.insert(dataSource, "uuid", "id", "value") {
            value(user.playerUUID, user.longId, json)
        }
    }

    public fun saveInDatabase(group: Group) {
        val json = Klaxon().toJsonString(group)
        userTable.insert(dataSource, "id", "value") {
            value(group.longId, json)
        }
    }

    public fun getUserInDatabase(player: Player?): User? {
        if (player == null) return null
        val uuid = player.uniqueId
        val string = userTable.select(dataSource) {
            rows("value")
            where("uuid" eq uuid)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
        if (string == null) return null else {
            return Klaxon().parse<User>(string)
            // Expansion fun import kotlinx.serialization.encodeToString required.
        }
    }

    public fun getUserInDatabase(id: Long?): User? {
        if (id == null) return null
        val string = userTable.select(dataSource) {
            rows("value")
            where("id" eq id)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
        if (string == null) return null else {
            return Klaxon().parse<User>(string)
        }
    }


    init {
        userTable.createTable(dataSource)
    }
}