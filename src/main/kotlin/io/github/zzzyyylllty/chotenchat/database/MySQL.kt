package io.github.zzzyyylllty.chotenchat.database

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.dataSource
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.host
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
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
        add("long_id") { // LongID
            type(ColumnTypeSQL.VARCHAR, 64) {
                // 创建索引
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.JSON)
        }
    }

    val groupTable = Table("group_table", host) {
        add { id() }
        add("long_id") { // LongID
            type(ColumnTypeSQL.VARCHAR, 36) {
                // 创建索引
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.JSON)
        }
    }

    public fun saveInDatabase(user: User) {
        val json = Klaxon().toJsonString(user)
        userTable.insert(dataSource, "uuid", "long_id", "value") {
            value(user.playerUUID, user.longId, json)
        }
    }

    public fun saveInDatabase(group: Group) {
        val json = Klaxon().toJsonString(group)
        userTable.insert(dataSource, "long_id", "value") {
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
            where("long_id" eq id)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
        if (string == null) return null else {
            val parsed = Klaxon().parse<User>(string)
            val uuid = UUID.fromString(parsed?.playerUUID)
            if (userMap[id] == null && parsed != null) userMap[id] = parsed
            if (parsed != null && playerAsUserMap[uuid] == null) playerAsUserMap[uuid] = id
            return parsed
        }
    }
    public fun getGroupInDatabase(id: Long?): Group? {
        if (id == null) return null
        val string = groupTable.select(dataSource) {
            rows("value")
            where("long_id" eq id)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
        if (string == null) return null else {
            val parsed = Klaxon().parse<Group>(string)
            if (loadedGroupMap[id] == null && parsed != null) loadedGroupMap[id] = parsed
            return Klaxon().parse<Group>(string)
        }
    }


    init {
        userTable.createTable(dataSource)
        groupTable.createTable(dataSource)
    }
}