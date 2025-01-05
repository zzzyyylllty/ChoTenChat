package io.github.zzzyyylllty.zaleplon.data

class ActiveTask(
    /*
    * 在玩家接取任务后存储在内存内的子任务
    *
    * Task     子任务本体
    * progress 子任务进度
    * */
    val task: Task,
    var progress: Number,
    var goal: Number,
    var addon: Addon,
)