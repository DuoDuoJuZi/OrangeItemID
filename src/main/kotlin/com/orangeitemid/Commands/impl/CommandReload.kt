package com.orangeitemid.Commands.impl

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.asLangText

object CommandReload {
    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            if (sender is Player) {

                val itemInHand = sender.inventory.itemInMainHand
                val itemID = itemInHand.type.name
                val GetItem = sender.asLangText("Get-Item")
                sender.sendMessage("$GetItem $itemID")
            } else {
                sender.sendMessage("只有玩家才能使用这个命令。")
            }
        }
    }
}