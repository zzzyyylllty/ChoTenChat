package io.github.zzzyyylllty.zaleplon.data

data class PlayerQuestData(
    var quests: LinkedHashMap<String, ActiveQuest>,
    var trackingQuest: Quest,
)
