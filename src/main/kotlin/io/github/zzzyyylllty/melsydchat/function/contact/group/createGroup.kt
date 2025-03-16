package io.github.zzzyyylllty.melsydchat.function.contact.group

import io.github.zzzyyylllty.melsydchat.data.ContactIDContainer
import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.Group
import io.github.zzzyyylllty.melsydchat.data.MemberData
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import taboolib.common.util.random
import java.util.UUID

fun createGroup(id: Long = random(100,999999).toLong(), owner: User, groupName: String,groupMember): Group {

    var members = LinkedHashMap<Long, MemberData>()
    members.put()
    val group = Group(
        idData = ContactIDContainer(
            id = id,
            type = ContactType.GROUP,
        ),
        name = groupName,
        avatar = null,
        groupMember = TODO()
    )
    return group
}
