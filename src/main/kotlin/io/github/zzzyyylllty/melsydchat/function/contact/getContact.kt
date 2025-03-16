package io.github.zzzyyylllty.melsydchat.function.contact

import io.github.zzzyyylllty.melsydchat.data.Contact
import io.github.zzzyyylllty.melsydchat.data.ContactIDContainer
import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.ContactType.*
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.loadedGroupMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap

fun Long.asContact(type: ContactType): Contact? {
    return when (type) {
        USER -> userMap[this]
        GROUP -> loadedGroupMap[this]
    }
}
