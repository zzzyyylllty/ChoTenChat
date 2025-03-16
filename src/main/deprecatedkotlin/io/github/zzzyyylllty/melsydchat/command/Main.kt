package io.github.zzzyyylllty.melsydchat.command

/**
 * Usage: /dylsemchat
 *          ├── group [groupID]
 *          │      ├── operation
 *          │      │      ├── create <owner>
 *          │      │      ├── transfer
 *          │      │      └── disband
 *          │      ├── member
 *          │              ├── permission <user> <permissionGroup>
 *          │              ├── kick <user>
 *          │              └── blacklist <user>
 *          ├── message
 *          │      ├── send <group> <message>
 *          │      ├── reply <group> <replyMessageUUID> <message>
 *          │      └── recall <group> <recallMessageUUID>
 *          ├── announcement
 *          │      ├── send <group> <settings> <announcement>
 *          │      └── delete <group> <announcementUUID>
 *          ├── profilecard <user>
 *          │      ├── send <group> <settings> <announcement>
 *          │      └── delete <group> <announcementUUID>
 *
 * */