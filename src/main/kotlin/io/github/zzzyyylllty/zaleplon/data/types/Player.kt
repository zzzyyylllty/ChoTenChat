package io.github.zzzyyylllty.zaleplon.data.types

import io.github.zzzyyylllty.zaleplon.Zaleplon.registeredObjectives
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerFishEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync
import taboolib.platform.util.killer

object Player {



    @SubscribeEvent
    fun onPlayerFish(e: PlayerFishEvent) {
        if (registeredObjectives.contains("PLAYER_FISH"))
            onPlayerFish1(e)
    }

    private fun onPlayerFish1(e: PlayerFishEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["ENTITY:ENTITYTYPE"] = e.caught?.type
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand?.name
            completeTasks(p, "PLAYER_FISH", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerChat(e: AsyncPlayerChatEvent) {
        if (registeredObjectives.contains("PLAYER_CHAT"))
            onPlayerChat1(e)
    }

    private fun onPlayerChat1(e: AsyncPlayerChatEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["MESSAGE:STRING"] = e.message
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "PLAYER_CHAT", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerSyncChat(e: PlayerChatEvent) {
        if (registeredObjectives.contains("PLAYER_SYNCCHAT"))
            onPlayerSyncChat1(e)
    }

    private fun onPlayerSyncChat1(e: PlayerChatEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["MESSAGE:STRING"] = e.message
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "PLAYER_SYNCCHAT", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerChangeWorld(e: PlayerChangedWorldEvent) {
        if (registeredObjectives.contains("PLAYER_CHANGEWORLD"))
            onPlayerChangeWorld1(e)
    }

    private fun onPlayerChangeWorld1(e: PlayerChangedWorldEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["FROM:STRING"] = e.from.name
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "PLAYER_CHANGEWORLD", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerKillMob(e: EntityDeathEvent) {
        if (e.killer is Player && registeredObjectives.contains("PLAYER_KILLMOB"))
            onPlayerKillMob1(e)
    }

    private fun onPlayerKillMob1(e: EntityDeathEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.killer as Player
            metaList["ENTITYTYPE"] = e.entityType
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "PLAYER_KILLMOB", 1, metaList)
        }
    }


}
