package io.github.zzzyyylllty.zaleplon.functions

import taboolib.common.platform.function.severe

fun compare(i: Double, s: String, errorMessage: String): Boolean {
    if (s.startsWith(">=")) return (i >= s.split(" ")[1].toDouble())
    if (s.startsWith("==")) return (i == s.split(" ")[1].toDouble())
    if (s.startsWith("<=")) return (i <= s.split(" ")[1].toDouble())
    if (s.startsWith(">")) return (i > s.split(" ")[1].toDouble())
    if (s.startsWith("<")) return (i < s.split(" ")[1].toDouble())
    if (s.contains("..")) return (i in ((s.split("..")[0].toDouble())..(s.split("..")[1].toDouble())))
    severe(errorMessage)
    return false
}

fun compare(i: String, s: String, errorMessage: String): Boolean {
    if (s.startsWith("==")) return (i == s.split(" ")[1])
    if (s.startsWith("!=")) return (i != s.split(" ")[1])
    severe(errorMessage)
    return false
}
