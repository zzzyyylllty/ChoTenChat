package io.github.zzzyyylllty.melsydchat.kether

/**
 * ## User
 *
 * 根据 Long ID 获取用户。不填写 根据 sender 获取。可能为 null
 *
 * Usage:
 * **user \[action]**
 *
 * - user
 * - user 1000000
 *
 * */

/**
 * ## Group
 *
 * 根据 Long ID 获取群组。可能为 null
 *
 * Usage:
 * **group \{action}**
 *
 * - group 1000000
 *
 * */

/**
 * ## User Player
 *
 * 根据 Bukkit 玩家名称 获取用户。可能为 null
 *
 * Usage:
 * **user_player \[action]**
 *
 * - user_player player name
 * - user_player "AmeChan"
 *
 * */

/**
 * ## Group-Message Send
 *
 * 以系统(不填as)或用户(填写as action)形式发送群聊消息，可指定 UUID，不填随机。
 * 不检测所需权限。如果需要检测权限，请额外撰写权限检测。
 *
 * Usage:
 * **group-message send content {action} to {action} [as {action}] [uuid {action}]**
 *
 * - group-message send content "Normal FRIEND message." to user 1000000 as user 20000505 uuid 8e8d3c30-b094-48cf-be80-092f29ae1270
 * - group-message send content "Normal GROUP message." to group 1000000 as user 20000505
 * - group-message send content "System FRIEND message." to user 1000000
 * - group-message send content "System GROUP message." to group 1000000
 *
 * */

/**
 * ## Announcement Send
 *
 * 以系统(不填at)或用户(填写at action)形式发送群聊公告，可指定 UUID，不填随机。
 * 不检测所需权限。如果需要检测权限，请额外撰写权限检测。
 *
 * Usage:
 * **group-announcement send content {action} to {action} [as {action}] [uuid {action}]**
 *
 * - group-announcement send content "Send By System" to 1000000
 * - group-announcement send content "I love P-Chan." to 1000000 as user 20000505 uuid ce0b0def-582d-48fa-b576-6bc8978d4124
 *
 * */

/**
 * ## Announcement Remove
 *
 * 以系统(不填as)或用户(填写as action)形式删除群聊公告
 * 不检测所需权限。如果需要检测权限，请额外撰写权限检测。
 *
 * Usage:
 * **group-announcement send content {action} to {action} [as {action}]**
 *
 * - group-announcement send content "Normal message." to user 1000000 as user 20000505
 *
 * */

/**
 * ## Group-Permission
 *
 * 检查或给予用户特定权限ID。
 * 返回值:
 * detect时，返回是否具有特定权限ID
 * grant/revoke时，返回给予/撤销是否成功。若没有这个权限ID存在会返回null。
 *
 * Usage:
 * **group-permission (detect|grant|revoke) {action} in {action} at {token}**
 *
 * - group-permission detect "GROUP_OWNER" in group 1000000 at user 20200505
 * - group-permission grant "MESSAGE_SEND" in group 1000000 at user 20200505
 * - group-permission revoke "MESSAGE_SEND" in group 1000000 at user 20200505
 *
 * */

/**
 * ## Title
 *
 * 检查或给予用户特定权限ID。
 * 返回值:
 * detect时，返回是否具有特定权限ID
 * grant/revoke时，返回给予/撤销是否成功。若没有这个权限ID存在会返回null。
 *
 * Usage:
 * **title temperature in {action}**
 * **title special grant {action} in {action} at {token}**
 * **title special revoke {action} as {action}**
 *
 * - group-permission detect "GROUP_OWNER" in group 1000000 at user 20200505
 * - group-permission grant "MESSAGE_SEND" in group 1000000 at user 20200505
 * - group-permission revoke "MESSAGE_SEND" in group 1000000 at user 20200505
 *
 * */


class sendMessageKether {
}