package io.github.zzzyyylllty.zaleplon.data

data class ActiveQuest(
    /*
    * 在玩家接取任务后存储的任务
    *
    * QuestStat       任务状态，如未解锁等。
    * QuestDifficulty 内置的任务组件，任务的难度。
    * QuestCategory   内置的任务组件，任务的分类。
    * QuestMetas      任务的元数据，用于自定义任务信息与其他的功能。
    * QuestTasks      任务的动态子任务。
    *
    * */
    var questStat: QuestStat,
    var questDifficulty: String,
    var questCategory: String,
    var questMetas: LinkedHashMap<String, Any>,
    var questTasks: LinkedHashMap<String, ActiveTask>,
    var addon: Addon,
)