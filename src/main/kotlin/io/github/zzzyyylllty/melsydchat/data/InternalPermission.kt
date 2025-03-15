package io.github.zzzyyylllty.melsydchat.data

enum class InternalPermission {
    MANAGE_BLACKLIST,
    MANAGE_KICK,
    MANAGE_OWNER,
    MANAGE_SETTINGS,
    MESSAGE_READ,
    MESSAGE_SEND,
    MESSAGE_REPLY,
    MENTION_MEMBER,
    MENTION_ALL,
    RECALL_SELF,
    RECALL_OTHER,
    ANNOUNCEMENT_SEND,
    ANNOUNCEMENT_EDIT,
    SHOW_ITEM,
    MANAGE_DISSOLVE, // 解散
    INVITE_USER,
    INVITE_DIRECT, // 直接邀请进群无需管理同意
    GRANT_GROUP,
    REVOKE_GROUP,

    CRITICAL_PAPI, // 敏感权限，不给予非服务器管理，因为可以用%chemdah_xxx%执行Kether。
}
