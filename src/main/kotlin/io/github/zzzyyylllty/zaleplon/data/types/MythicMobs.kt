package io.github.zzzyyylllty.data.types


import io.github.zzzyyylllty.zaleplon.Zaleplon.registeredObjectives
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import io.lumine.mythic.bukkit.events.MythicMobInteractEvent
import org.bukkit.entity.Player
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Mythicmobs")
object MythicMobs {

    private var metaList = LinkedHashMap<String, Any?>()
    @SubscribeEvent
    fun onMythicMobsMobKill(e: MythicMobDeathEvent) {
        if (registeredObjectives.contains("MYTHICMOBS_KILL"))
            onMythicMobsMobKill1(e)
    }

    private fun onMythicMobsMobKill1(e: MythicMobDeathEvent) {
        submitAsync {
            if (e.killer is Player) {
                val p = e.killer as Player
                metaList["NAME:STRING"] = e.mobType.internalName
                metaList["MOBLEVEL:NUMBER"] = e.mobLevel
                metaList["X:NUMBER"] = e.entity.location.x
                metaList["Y:NUMBER"] = e.entity.location.y
                metaList["Z:NUMBER"] = e.entity.location.z
                metaList["PX:NUMBER"] = p.location.x
                metaList["PY:NUMBER"] = p.location.y
                metaList["PZ:NUMBER"] = p.location.z
                completeTasks(p, "MYTHICMOBS_KILL", 1, metaList)
            }
        }
    }

    @SubscribeEvent
    fun onMythicMobsMobInteract(e: MythicMobInteractEvent) {
        if (registeredObjectives.contains("MYTHICMOBS_INTERACT"))
            onMythicMobsMobInteract1(e)
    }

    private fun onMythicMobsMobInteract1(e: MythicMobInteractEvent) {
        submitAsync {
            val p = e.player
            metaList["NAME:STRING"] = e.activeMobType.internalName
            metaList["MOBLEVEL:NUMBER"] = e
            metaList["X:NUMBER"] = e.activeMob.location.x
            metaList["Y:NUMBER"] = e.activeMob.location.y
            metaList["Z:NUMBER"] = e.activeMob.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "MYTHICMOBS_INTERACT", 1, metaList)
        }
    }
}
