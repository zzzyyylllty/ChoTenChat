package io.github.zzzyyylllty.zaleplon.data.types

import io.github.zzzyyylllty.zaleplon.Zaleplon
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import net.citizensnpcs.api.event.NPCDamageByEntityEvent
import net.citizensnpcs.api.event.NPCRightClickEvent
import org.bukkit.entity.Player
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Citizens")
object CitizensQuests {

    @SubscribeEvent                                                                       // =EventHandler+RegisterEvents
    fun onCitizensNPCInteract(e: NPCRightClickEvent) {
        if (Zaleplon.registeredObjectives.contains("CITIZENS_INTERACT")) // If registered objects contains it
            onCitizensNPCInteract1(e)                                                    // Do
    }

    private fun onCitizensNPCInteract1(e: NPCRightClickEvent) {

        // Send BukkitRunnable
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.clicker
            metaList["ID:STRING"] = e.npc.id
            metaList["X:NUMBER"] = e.npc.storedLocation.x
            metaList["Y:NUMBER"] = e.npc.storedLocation.y
            metaList["Z:NUMBER"] = e.npc.storedLocation.z
            metaList["ITEMSTACK:MATERIAL"] = e.clicker.inventory.itemInMainHand
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "CITIZENS_INTERACT", 1, metaList)
        }
    }
}

@SubscribeEvent                                                                       // =EventHandler+RegisterEvents
fun onCitizensNPCInteract(e: NPCDamageByEntityEvent) {
    if (Zaleplon.registeredObjectives.contains("CITIZENS_DAMAGE")) // If registered objects contains it
        onCitizensNPCInteract1(e)                                                    // Do
}

private fun onCitizensNPCInteract1(e: NPCDamageByEntityEvent) {

    // Send BukkitRunnable
    submitAsync {
        val metaList = LinkedHashMap<String, Any?>()
        val p = e.damager
        if (p is Player) {
            metaList["ID:STRING"] = e.npc.id
            metaList["X:NUMBER"] = e.npc.storedLocation.x
            metaList["Y:NUMBER"] = e.npc.storedLocation.y
            metaList["Z:NUMBER"] = e.npc.storedLocation.z
            metaList["ITEMSTACK:MATERIAL"] = p.inventory.itemInMainHand
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "CITIZENS_DAMAGE", e.damage, metaList)
        }
    }
}

