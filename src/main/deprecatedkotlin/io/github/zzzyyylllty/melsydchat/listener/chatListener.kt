package io.github.zzzyyylllty.melsydchat.listener

import io.github.zzzyyylllty.melsydchat.data.ContactType
import io.github.zzzyyylllty.melsydchat.data.OtherAccount
import io.github.zzzyyylllty.melsydchat.data.ProfileCard
import io.github.zzzyyylllty.melsydchat.data.User
import io.github.zzzyyylllty.melsydchat.data.UserData
import io.github.zzzyyylllty.melsydchat.data.selectableContact
import io.github.zzzyyylllty.melsydchat.function.group.asUser
import io.github.zzzyyylllty.melsydchat.function.message.buildMessage
import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userDataMap
import main.kotlin.io.github.zzzyyylllty.zaleplon.DelsymChat.userMap
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import java.util.UUID

@SubscribeEvent(EventPriority.LOWEST)
fun onChat(e: AsyncChatEvent) {
    e.renderer(ChatRenderer { source: Player, sourceDisplayName: Component, message: Component, viewer: Audience ->
        buildMessage(
            source, sourceDisplayName, message, viewer,
            subscribedContact = e.player.asUser()?.data().subscribeContact
        )
    })
    Bukkit.broadcastMessage("1")
}

@SubscribeEvent(EventPriority.MONITOR)
fun onJoin(e: PlayerJoinEvent) {
    // create

    if (userMap[e.player.uniqueId] == null) {
        val createdUser = User(
            playerName = e.player.name,
            nickname = e.player.name,
            profileCard = ProfileCard(
                lore = "这个宅宅很懒，还没有个性签名哦",
                otherAccount = OtherAccount(
                    qq = null,
                    oopz = null,
                    email = null,
                    discord = null,
                    github = null,
                ),
                tags = listOf(),
            ),
            id = 1000000,
            uuid = UUID.randomUUID(),
            typeId = ContactType.STANDARD,
            remark = null,
            avatar = null,
            name = e.player.name,
        )
        userMap.put(e.player.uniqueId, createdUser)
    }

    val user = userMap[e.player.uniqueId] ?: throw NullPointerException()

    if (userDataMap[user] == null) {
        val createdData = UserData(
            subscribeContact = listOf(
                selectableContact(
                    1000000,
                    ContactType.GROUP
                )
            ),
            contactsList = listOf(
                selectableContact(
                    1000000,
                    ContactType.GROUP
                )
            ),
            topedContactsList = listOf(
                selectableContact(
                    1000000,
                    ContactType.GROUP
                )
            ),
            userMeta = LinkedHashMap<String, String>()
        )
        userDataMap.put(user, createdData)
    }


}