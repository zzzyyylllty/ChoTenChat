package io.github.zzzyyylllty.melsydchat.function.contact.group

import io.github.zzzyyylllty.melsydchat.data.MemberData
import io.github.zzzyyylllty.melsydchat.data.TitleType

fun emptyMemberData(): MemberData {
    return MemberData(
        titleSelection = TitleType.TEMPERATURE,
        temperature = 0,
        isMuted = false,
        muteTimeEnd = null,
        groupName = null
    )
}