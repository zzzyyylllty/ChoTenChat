package io.github.zzzyyylllty.chotenchat.function.contactOperatrion

import io.github.zzzyyylllty.chotenchat.data.Contact
import io.github.zzzyyylllty.chotenchat.data.ContainedContact
import io.github.zzzyyylllty.chotenchat.data.ContainedContactType
import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.data.TitleSelection
import io.github.zzzyyylllty.chotenchat.data.User
import io.github.zzzyyylllty.chotenchat.data.UserData
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asOrCreateUser
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.logger.infoL
import io.github.zzzyyylllty.chotenchat.logger.severeS
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.consoleSender
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.playerAsUserMap
import main.kotlin.io.github.zzzyyylllty.chotenchat.ChoTenChat.userMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandContext
import taboolib.common.platform.command.player
import taboolib.common.util.random
import taboolib.platform.util.asLangText
import kotlin.collections.set


fun Player?.createUser(
    regName: String,
    nickName: String?,
    longId: Long,
    idData: IdData = IdData(
        fancyAccountType = FancyAccountType.NORMAL,
        fancyAccountValue = 0
    ),
    userData : UserData = UserData(
        subscribeContact = null,
        contactSettings = LinkedHashMap()
    ),
    sender: CommandSender = consoleSender) {
    if (longId.asGroup() == null) {
        this.createOrWipeUser(
            regName,
            nickName,
            longId,
            idData,
            userData
        )
    } else sender.severeS(consoleSender.asLangText("GROUP_CREATE_FAIL_EXISTED", longId))
}

fun Player?.createOrWipeUser(
    regName: String = this?.player?.name ?: "UnName User",
    nickName: String? = null,
    longId: Long = generateRandomUserId(),
    idData: IdData = IdData(
        fancyAccountType = FancyAccountType.NORMAL,
        fancyAccountValue = 0
    ),
    userData : UserData = UserData(
            subscribeContact = null,
    contactSettings = LinkedHashMap()
)
): User {

    val player = this
    val user = User(
        registryName = regName, // TODO
        nickName = null,
        longId = 1000000,
        idData = idData,
        playerUUID = player?.uniqueId.toString(),
        playerName = player?.name,
        data = UserData(
            subscribeContact = null,
            contactSettings = LinkedHashMap()
        )
    )
    val id = user.longId
    userMap[id] = user
    if (this != null) {
        playerAsUserMap[this.uniqueId] = id
    }
    infoL("INTERNAL_INFO_CREATING_USER", this?.name ?: "<No Name>" ,user)
    return user
}

fun User.asMember(g: Long, permission: GroupPermission) : Member {
    return Member(
        groupName = this.getNickOrReg(),
        longId = longId,
        group = g,
        playerUUID = playerUUID,
        playerName = playerName,
        temperature = 0,
        specialTitle = null,
        groupPermission = permission,
        titleSelection = TitleSelection.TEMPERATURE,
        titleTheme = "default"
    )
}

fun User.getMember(g: Long) : Member? {
    return g.asGroup()?.members?.get(this.longId)
}


fun generateRandomUserId() : Long{
    var randomId = 0
    while (true) {
        randomId = random(100000,999999)
        if (randomId.toLong().asUser() == null) break
    }
    return randomId.toLong()
}

fun User.subscribeContact(contact: Contact) {
    this.data.subscribeContact = contact.toContained()
}

fun Contact.toContained(): ContainedContact {
    return when (this) {
        is Group -> {
            ContainedContact(ContainedContactType.GROUP, this.longId)
        }

        is User -> {
            ContainedContact(ContainedContactType.USER, this.longId)
        }

        else -> {
            throw IllegalStateException("Not a Group Or User")
        }
    }
}

fun ContainedContact.toUnContained(): Contact? {
    return when (this.contactType) {
        ContainedContactType.GROUP -> {
            this.longId.asGroup()
        }
        ContainedContactType.USER -> {
            this.longId.asUser()
        }
    }
}

fun tabooPlayerAsUser(sender: CommandSender, context: CommandContext<CommandSender>): User {
    val tabooPlayer = context.player("player")
    val bukkitPlayer = tabooPlayer.castSafely<Player>()
    if (bukkitPlayer == null) {
        sender.severeS(sender.asLangText("PLAYER_NOT_FOUND", context["player"]), true)
        throw NullPointerException()
    }
    return bukkitPlayer.asOrCreateUser()
}