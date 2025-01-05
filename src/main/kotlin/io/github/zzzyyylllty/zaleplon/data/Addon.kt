package io.github.zzzyyylllty.zaleplon.data

import org.bukkit.inventory.ItemStack

data class Addon(
    val gui: AddonGui?,
    val dependency: ArrayList<AddonDependencySingle>?,
    val agent: Agent?,
)

data class AddonGui(
    val material: ItemStack?,
    val nameStandard: String?,
    val nameTracking: String?,
    val nameCompleted: String?,
    val loreeStandard: List<String?>?,
    val loreTracking: List<String?>?,
    val loreCompleted: List<String?>?,
    val showBefore: Boolean?,
    val showProgressing: Boolean?,
    val showCompleted: Boolean?,
)

data class AddonDependencySingle(
    val conditions: List<String>?,
    val quests: List<String>?,
)

data class Agent(
    val onComplete: String?,
    val onFail: String?,
    val onAccept: String?,
    val onRestart: String?,
    val onProgress: String?, // 只适用于task
    val onProgressFail: String?, // 只适用于task
)


data class Control(
    val onComplete: String?,
    val onFail: String?,
    val onAccept: String?,
    val onRestart: String?,
    val onProgress: String?, // 只适用于task
    val onProgressFail: String?, // 只适用于task
)

