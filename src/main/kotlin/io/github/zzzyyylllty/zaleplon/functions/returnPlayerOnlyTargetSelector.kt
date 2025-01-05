package io.github.zzzyyylllty.zaleplon.functions

import org.bukkit.Bukkit.getOnlinePlayers
import java.util.stream.Collectors


fun returnPlayerOnlyTargetSelector(): List<String> {
    val list1 = getOnlinePlayers().stream().map { list1 ->
        val list2: String = String()
        list2
    }
        .collect(Collectors.toList())
    list1.add("@a")
    list1.add("@p")
    list1.add("@r")
    list1.add("@s")
    return list1
}
