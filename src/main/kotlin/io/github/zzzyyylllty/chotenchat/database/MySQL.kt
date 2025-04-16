package io.github.zzzyyylllty.chotenchat.database

import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.User
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.config
val jsonUtils = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = true
    allowStructuredMapKeys = true
    allowSpecialFloatingPointValues = true
}

public open class SQLDataBase {

    val host by lazy { config.getHost("database") }
    val dataSource by lazy { host.createDataSource() }

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
        val json = jsonUtils.encodeToString(user)
        userTable.insert(dataSource,"uuid", "long_id", "value") {
            onDuplicateKeyUpdate { value(user.playerUUID, user.playerUUID, user.longId, json) }
        }
        userTable.update(dataSource) {
            set("long_id", user.longId)
            set("value", json)
            where("uuid" eq user.playerUUID)
        }
        /*
        userTable.insert(dataSource, "uuid", "long_id", "value") {
            value(user.playerUUID, user.longId, json)
        }
        */

    }

    public fun saveInDatabase(group: Group) {
        val json = jsonUtils.encodeToString(group)
        groupTable.insert(dataSource, "long_id", "value") {
            onDuplicateKeyUpdate { value(group.longId, json) }
        }
        groupTable.update(dataSource) {
            set("value", json)
            where("long_id" eq group.longId)
        }
        /*
        groupTable.insert(dataSource, "long_id", "value") {
            onDuplicateKeyUpdate { value(group.longId, json) }
        }*/

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
            return jsonUtils.decodeFromString<User>(string)
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
            val parsed = jsonUtils.decodeFromString<User?>(string)
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
            val parsed = jsonUtils.decodeFromString<Group?>(string)
            if (loadedGroupMap[id] == null && parsed != null) loadedGroupMap[id] = parsed
            return parsed
        }
    }


    init {
        userTable.createTable(dataSource)
        groupTable.createTable(dataSource)
    }
}