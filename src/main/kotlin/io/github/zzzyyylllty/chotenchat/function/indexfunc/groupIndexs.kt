package io.github.zzzyyylllty.chotenchat.function.indexfunc

import io.github.zzzyyylllty.chotenchat.data.FancyAccountType
import io.github.zzzyyylllty.chotenchat.data.Group
import io.github.zzzyyylllty.chotenchat.data.GroupPermission
import io.github.zzzyyylllty.chotenchat.data.IdData
import io.github.zzzyyylllty.chotenchat.data.Member
import io.github.zzzyyylllty.chotenchat.function.bukkitPlayer.asUser
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.asMember
import io.github.zzzyyylllty.chotenchat.function.contactOperatrion.createOrWipeGroup
import io.github.zzzyyylllty.chotenchat.logger.infoS
import org.bukkit.entity.Player
import taboolib.platform.util.asLangText
import taboolib.platform.util.inputBook
import taboolib.platform.util.nextChat
import taboolib.platform.util.nextChatInTick

fun Player.createGroupInIndex() {
    val player = this


    var registryName : String
    var longId : Long
    var fancyAccountType : FancyAccountType
    var fancyAccountValue : Int
    var members = LinkedHashMap<Long, Member>()

    player.infoS(player.asLangText("INDEX_CREATING_GROUP"))
    player.infoS(player.asLangText("INDEX_CREATING_GROUP_TYPING_ID"))
    player.nextChatInTick(20 * 20, {

        player.infoS(player.asLangText("INDEX_TYPED",it))
        longId = it.toLong()
        player.infoS(player.asLangText("INDEX_CREATING_GROUP_TYPING_REGNAME"))

        player.nextChat { it ->  // NAME

            player.infoS(player.asLangText("INDEX_TYPED", it))
            registryName = it

            player.infoS(player.asLangText("INDEX_CREATING_GROUP_TYPING_FANCY"))
            player.nextChat { it -> // FANCY

                player.infoS(player.asLangText("INDEX_TYPED", it))
                val split = if (it != "~") it.split(",") else listOf("NORMAL", "0")
                fancyAccountType = FancyAccountType.valueOf(split[0])
                fancyAccountValue = if (split.size >= 2) split[1].toInt() else 0

                player.infoS(player.asLangText("INDEX_CREATING_GROUP_TYPING_MEMBER"))
                player.nextChat { it -> // MEMBER

                    player.infoS(player.asLangText("INDEX_TYPED", it))
                    val split = it.split(",")
                    val memberList = LinkedHashMap<Long, Member>()
                    for (memberId in split) {
                        val memberLong = memberId.toLong()
                        memberLong.asUser()?.let { it1 -> members.put(memberLong,it1.asMember(longId, GroupPermission.MEMBER)) }
                    }
                    split[0].toLong().asUser()?.let { it1 -> members[split[0].toLong()] = it1.asMember(longId, GroupPermission.OWNER) }
                    val creator = split[0].toLong().asUser()!!

                    createOrWipeGroup(
                        longId, creator,
                        regName = registryName,
                        idData = IdData(
                            fancyAccountType = fancyAccountType,
                            fancyAccountValue = fancyAccountValue
                        ),
                    )

                }
            }

        }

    }, {
        // 超时回调
        player.infoS(player.asLangText("INDEX_TIMED_OUT"))
    }, {
        // 取消回调
        player.infoS(player.asLangText("INDEX_CANCELED"))
    })
}