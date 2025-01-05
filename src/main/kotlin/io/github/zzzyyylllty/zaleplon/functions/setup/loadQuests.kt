package io.github.zzzyyylllty.zaleplon.functions.setup

import io.github.zzzyyylllty.zaleplon.Zaleplon.console
import io.github.zzzyyylllty.zaleplon.Zaleplon.loadedQuests
import io.github.zzzyyylllty.zaleplon.data.Addon
import io.github.zzzyyylllty.zaleplon.data.Objective
import io.github.zzzyyylllty.zaleplon.data.Quest
import io.github.zzzyyylllty.zaleplon.data.Task
import io.github.zzzyyylllty.zaleplon.functions.loadAddon
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import taboolib.common.util.asList
import taboolib.module.lang.asLangText
import java.io.File

fun loadQuests() {
    info(console.asLangText("LOG_READ_QUEST_START"))

    val files = File(getDataFolder(), "quests").listFiles() ?: run {
        info(console.asLangText("LOG_READ_QUEST_START"))
        return
    }

    if (!File(getDataFolder(), "quests").exists()) {
        releaseResourceFile("quests/example.yml")
    }

    for (file in files) {
        try {
            info("正在处理任务文件 ${file.getName()} ...")
            loadSingleQuestFile(file)
            info("正在处理任务文件 ${file.getName()} ... 完成.")
        } catch (exception: IllegalArgumentException) {
            error("处理任务文件 ${file.getName()} 时发生意外错误 \n Stacktrace: $exception")
        }
    }

    info("任务加载完成!")
}


fun loadSingleQuestFile(file: File) {

    val config = YamlConfiguration.loadConfiguration(file)
    info(config.getKeys(true))

    for (quest in config.getKeys(false)) {

        val keys = config.getKeys(true)

        val questDifficulty = config["quest.meta.difficulty"].toString()
        val questCategory = config["quest.meta.category"]?.asList()
        val questTasks = LinkedHashMap<String, Task>()
        val questMetas = LinkedHashMap<String, Any>()
        val loadedTasks = ArrayList<String>()

        for (nodes in keys) {
            val node = nodes.split(".")
            if (node.size == 3 && node[1] == "meta" && node[0] == quest) {
                questMetas[node[2]] = config[node[2]] ?: "None"
            } else if (node[1] == "tasks" && node[0] == quest && loadedTasks.contains(
                    nodes.split(
                        "."
                    )[2]
                )
            ) {
                val taskid = node[2]
                loadedTasks.add(taskid)
                var i = 1
                val objectives = ArrayList<Objective>()
                while (true) {
                    if (config["$quest.tasks.$taskid.display.objectives.$i"] == null) {
                        if (i == 1) error(console.asLangText("DEBUG_NO_OBJECTIVES", quest, taskid))
                        break
                    }

                    val meta = LinkedHashMap<String, Any>()
                    for (key in keys) {
                        if (key.startsWith("$quest.tasks.$taskid.display.objectives.$i.meta")) {
                            val keysplit = key.split(".")
                            val metakey = keysplit[keysplit.size - 1]
                            val metavalue = config["$quest.tasks.$taskid.display.objectives.$i.meta.$metakey"]
                            if (metavalue != null) meta.put(metakey, metavalue) else {
                                error(console.asLangText("DEBUG_NO_META", quest, taskid, i, metakey))
                            }
                        }

                    }

                    objectives[i] = Objective(
                        config["$quest.tasks.$taskid.display.objectives.$i.objective"].toString(),
                        meta,
                        config["$quest.tasks.$taskid.display.objectives.$i.requirement"].toString(),
                        config["$quest.tasks.$taskid.display.objectives.$i.run"].toString(),
                    )
                    i++
                }

                var taskaddon: Addon






                questTasks[taskid] = Task(
                    config["$quest.tasks.$taskid.display.taskname"].toString(),
                    config["$quest.tasks.$taskid.display.tasklore"].toString(), // TODO TASKLORE: AUTO
                    objectives,
                    config["$quest.tasks.$taskid.display.amount"] as Number,
                    config["$quest.tasks.$taskid.display.taskname"].toString()
                )
            }
        }

        val questaddon: Addon = loadAddon(
            config, Quest(
                config["$quest.name"].toString(),
                config["$quest.description"]?.asList(),
                questDifficulty,
                questCategory,
                questMetas,
                questTasks,
                null
            ),
            null, null,
        )


        loadedQuests[quest] = Quest(
            config["$quest.name"].toString(),
            config["$quest.description"]?.asList(),
            questDifficulty,
            questCategory,
            questMetas,
            questTasks,
            questaddon
            )

    }
}

