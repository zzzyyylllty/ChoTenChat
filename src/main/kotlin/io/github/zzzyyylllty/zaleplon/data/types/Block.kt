package io.github.zzzyyylllty.zaleplon.data.types

import io.github.zzzyyylllty.zaleplon.Zaleplon
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockIgniteEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerHarvestBlockEvent
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

object BlockObjectives {

    @SubscribeEvent
    fun onBlockHarvest(e: PlayerHarvestBlockEvent) {
        if (Zaleplon.registeredObjectives.contains("BLOCK_HARVEST"))
            onBlockHarvest1(e)
    }


    private fun onBlockHarvest1(e: PlayerHarvestBlockEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["BLOCK:MATERIAL"] = e.harvestedBlock.type
            metaList["X:NUMBER"] = e.harvestedBlock.location.x
            metaList["Y:NUMBER"] = e.harvestedBlock.location.y
            metaList["Z:NUMBER"] = e.harvestedBlock.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand.name
            completeTasks(p, "BLOCK_HARVEST", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onBlockBreak(e: BlockBreakEvent) {
        if (Zaleplon.registeredObjectives.contains("BLOCK_BREAK"))
            onBlockBreak(e)
    }

    private fun onBlockBreak1(e: BlockBreakEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["BLOCK:MATERIAL"] = e.block.type
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["EXP:INT"] = e.expToDrop
            completeTasks(p, "BLOCK_BREAK", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (Zaleplon.registeredObjectives.contains("BLOCK_PLACE"))
            onBlockPlace1(e)
    }
    private fun onBlockPlace1(e: BlockPlaceEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["BLOCK:MATERIAL"] = e.block.type
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand.name
            metaList["ITEMSTACK:MATERIAL"] = e.itemInHand
            completeTasks(p, "BLOCK_PLACE", 1, metaList)
        }
    }

    @SubscribeEvent
    fun onBlockIgnite(e: BlockIgniteEvent) {
        if (Zaleplon.registeredObjectives.contains("BLOCK_IGNITE"))
            onBlockIgnite1(e)
    }
    private fun onBlockIgnite1(e: BlockIgniteEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["BLOCK:MATERIAL"] = e.block.type
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p?.location?.x
            metaList["PY:NUMBER"] = p?.location?.y
            metaList["PZ:NUMBER"] = p?.location?.z
            metaList["IGNITECAUSE:IGNITECAUSE"] = e.cause.name
            metaList["BLOCK:IGNITINGBLOCK"] = e.ignitingBlock
            completeTasks(p, "BLOCK_IGNITE", 1, metaList)
        }
    }


    @SubscribeEvent
    fun onBlockInteract(e: PlayerInteractEvent) {
        if (Zaleplon.registeredObjectives.contains("BLOCK_INTERACT"))
            onBlockInteract1(e)
    }

    private fun onBlockInteract1(e: PlayerInteractEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            if (e.clickedBlock != null) {
                val p = e.player
                val block = e.clickedBlock ?: return@submitAsync
                metaList["BLOCK:MATERIAL"] = block.type
                metaList["X:NUMBER"] = block.location.x
                metaList["Y:NUMBER"] = block.location.y
                metaList["Z:NUMBER"] = block.location.z
                metaList["PX:NUMBER"] = p.location.x
                metaList["PY:NUMBER"] = p.location.y
                metaList["PZ:NUMBER"] = p.location.z
                metaList["HAND:EQUIPMENTSLOT"] = e.hand?.name
                metaList["ITEMSTACK:MATERIAL"] = e.item
                metaList["ACTION:ACTION"] = e.action.name
                completeTasks(p, "BLOCK_INTERACT", 1, metaList)
            }
        }
    }


}