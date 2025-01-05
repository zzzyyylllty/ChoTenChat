package io.github.zzzyyylllty.zaleplon.data

data class Task(
    /*
    * 静态子任务
    *
    * TaskDisplayName 子任务显示名称，若为 null 则使用默认名称
    * TaskDisplayLore 子任务显示介绍，若为 null 则使用默认介绍或不显示介绍(取决于配置)
    * TaskObjectives  子任务目标
    * Requirement     Kether 要求
    * Requirement     Kether 执行
    * */
    var taskDisplayName: String?,
    var taskDisplayLore: String?,
    var taskObjectives: ArrayList<Objective>,
    var amount: Number,
    var addon: Addon?,
)
