package com.orangeitemid.Commands.impl

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptCommandSender
import taboolib.platform.util.asLangText
import taboolib.module.chat.TellrawJson
import net.md_5.bungee.api.chat.*
import taboolib.platform.util.sendLang
import dev.lone.itemsadder.api.ItemsAdder

object CommandGet {
    @CommandBody
    val get = subCommand {
        execute<CommandSender> { sender, _, _ ->
            if (sender is Player) {

                val itemInHand = sender.inventory.itemInMainHand

                val itemID = if (CommandCase.isUpperCase) {
                    itemInHand.type.name.toUpperCase()
                } else {
                    itemInHand.type.name.toLowerCase()
                }


                val GetItem = sender.asLangText("Get-Item")
                val CopyItemId = sender.asLangText("Copy-Item-Id")
                val message = TextComponent(GetItem)
                message.addExtra(TextComponent(" $itemID "))
                val CopyItemText = TextComponent(CopyItemId)
                CopyItemText.clickEvent = ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "$itemID")
                CopyItemText.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("点击复制 $itemID 到剪贴板")))
                message.addExtra(CopyItemText)

                sender.spigot().sendMessage(message)
            } else {

                sender.sendLang("Only-Player-Command")
            }
        }
    }
}
