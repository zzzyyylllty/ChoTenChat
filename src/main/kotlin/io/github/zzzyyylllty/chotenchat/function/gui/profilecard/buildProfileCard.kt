package io.github.zzzyyylllty.chotenchat.function.gui.profilecard

import io.github.zzzyyylllty.chotenchat.data.Contact
import io.github.zzzyyylllty.chotenchat.data.User
import org.bukkit.entity.Player
import taboolib.library.xseries.XMaterial
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Chest
import taboolib.platform.util.buildItem

fun Player.openProfileCard(contact: Contact) {
    if (contact is User) {
        this.openMenu<Chest>("箱子标题") {
            map(
                "#########",
                "#   A   #",
                "#########",
                "#########",
                "#   A   #",
                "#########",
            )
            set('A', buildItem(XMaterial.APPLE) {
                name = "&a苹果"
                lore.add("&f这是一个苹果")
                colored()
            }) {
                player?.sendMessage("点击了苹果")
            }
        }
    }
}
