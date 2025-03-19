package io.github.zzzyyylllty.chotenchat.function.datasave

import com.beust.klaxon.Klaxon
import io.github.zzzyyylllty.chotenchat.data.User

fun UserToJson(input: User): String {
    try {
        //注意转换成功的json是String类型
        return Klaxon().toJsonString(input)
    } catch (e: Exception)
    {
        e.printStackTrace()
    }
    throw Exception()
}
fun JsonToUser(input: String): User? {
    return Klaxon().parse<User>(input)
}
