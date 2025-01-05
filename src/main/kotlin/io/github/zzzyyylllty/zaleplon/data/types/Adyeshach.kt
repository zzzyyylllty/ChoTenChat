package io.github.zzzyyylllty.zaleplon.data.types

import ink.ptms.adyeshach.core.event.AdyeshachEntityInteractEvent
import io.github.zzzyyylllty.zaleplon.Zaleplon
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Adyeshach")
object AdyeshachQuests {

    @SubscribeEvent                                                                       // =EventHandler+RegisterEvents
    fun onAdyeshachNPCInteract(e: AdyeshachEntityInteractEvent) {
        if (Zaleplon.registeredObjectives.contains("ADYESHACH_INTERACT")) // If registered objects contains it
            onAdyeshachNPCInteract1(e)                                                    // Do
    }

    private fun onAdyeshachNPCInteract1(e: AdyeshachEntityInteractEvent) {

        // Send BukkitRunnable
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["ID:STRING"] = e.entity.id
            metaList["VECTOR:VECTOR"] = e.vector
            metaList["ITEMSTACK:MATERIAL"] =
                if (e.isMainHand) e.player.inventory.itemInMainHand else e.player.inventory.itemInOffHand
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "ADYESHACH_INTERACT", 1, metaList)
        }
    }
}
