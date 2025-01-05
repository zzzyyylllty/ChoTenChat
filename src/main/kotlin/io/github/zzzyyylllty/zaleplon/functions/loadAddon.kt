package io.github.zzzyyylllty.zaleplon.functions

import io.github.zzzyyylllty.zaleplon.data.*
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import taboolib.common.util.asList

fun loadAddon(config: YamlConfiguration, quest: Quest, task: Task?, taskid: String?): Addon {

    var gui: AddonGui? = null
    val dependency: ArrayList<AddonDependencySingle>? = null
    var agent: Agent? = null

    val questname = quest.questDisplayName ?: "<null>"
    val questdesc = quest.questDisplayLore ?: "<null>"

    fun resolvePlaceholdersString(string: String): String {
        string.replace("{questname}", questname)
        string.replace("{name}", questname)
        PlaceholderAPI.setPlaceholders(null, string)
        return string
    }

    fun resolvePlaceholdersList(list: ArrayList<String>): ArrayList<String> {
        val returns = ArrayList<String>()
        for (s in list) {
            returns.add(solvePapi(resolvePlaceholdersString(s), null))
        }
        return returns
    }

    var questGuiItem = ItemStack(Material.valueOf(config["$quest.addon.gui.material"].toString()))
    if (config["$quest.addon.gui.glow"] == true) questGuiItem.enchantments[Enchantment.LUCK] = 1
    if (config["$quest.addon.gui.model-data"] != null) questGuiItem.itemMeta?.setCustomModelData(
        config["$quest.addon.gui.model-data"].toString().toInt()
    )


    if (task == null) {
        gui = AddonGui(
            questGuiItem,
            config["$quest.addon.gui.display-name.standard"].toString(),
            config["$quest.addon.gui.display-name.tracking"].toString(),
            config["$quest.addon.gui.display-name.completed"].toString(),
            config["$quest.addon.gui.display-lore.standard"]?.asList(),
            config["$quest.addon.gui.display-lore.tracking"]?.asList(),
            config["$quest.addon.gui.display-lore.completed"]?.asList(),
            config["$quest.addon.gui.show.before"].toString().toBoolean(),
            config["$quest.addon.gui.show.progressing"].toString().toBoolean(),
            config["$quest.addon.gui.show.completed"].toString().toBoolean(),
        )
        val i = 1
        while (config["$quest.addon.dependency"] == null) {
            dependency?.add(
                AddonDependencySingle(
                    config["$quest.addon.dependency.$i.conditions"]?.asList(),
                    config["$quest.addon.dependency.$i.quests"]?.asList(),
                )
            )
        }
        agent = Agent(
            config["$quest.addon.ketheragent.on-accept"].toString(),
            config["$quest.addon.ketheragent.on-complete"].toString(),
            config["$quest.addon.ketheragent.on-fail"].toString(),
            config["$quest.addon.ketheragent.on-restart"].toString(),
            null,
            null
        )
    } else {
        gui = AddonGui(
            questGuiItem,
            (config["$quest.tasks.$taskid.addon.gui.display-name.standard"]
                ?: config["$quest.addon.gui.display-name.standard"]).toString(),
            (config["$quest.tasks.$taskid.addon.gui.display-name.tracking"]
                ?: config["$quest.addon.gui.display-name.tracking"]).toString(),
            (config["$quest.tasks.$taskid.addon.gui.display-name.completed"]
                ?: config["$quest.addon.gui.display-name.completed"]).toString(),
            (config["$quest.tasks.$taskid.addon.gui.display-lore.standard"]
                ?: config["$quest.addon.gui.display-lore.standard"])?.asList(),
            (config["$quest.tasks.$taskid.addon.gui.display-lore.tracking"]
                ?: config["$quest.addon.gui.display-lore.tracking"])?.asList(),
            (config["$quest.tasks.$taskid.addon.gui.display-lore.completed"]
                ?: config["$quest.addon.gui.display-lore.completed"])?.asList(),
            (config["$quest.tasks.$taskid.addon.gui.show.before"] ?: config["$quest.addon.gui.show.before"]).toString()
                .toBoolean(),
            (config["$quest.tasks.$taskid.addon.gui.show.progressing"]
                ?: config["$quest.addon.gui.show.progressing"]).toString().toBoolean(),
            (config["$quest.tasks.$taskid.addon.gui.show.completed"]
                ?: config["$quest.addon.gui.show.completed"]).toString().toBoolean(),
        )
        val i = 1
        while (config["$quest.tasks.$taskid.addon.dependency"] == null) {
            dependency?.add(
                AddonDependencySingle(
                    config["$quest.tasks.$taskid.addon.dependency.$i.conditions"]?.asList(),
                    config["$quest.tasks.$taskid.addon.dependency.$i.quests"]?.asList(),
                )
            )
        }
    }



    return Addon(gui, dependency, agent)
}

