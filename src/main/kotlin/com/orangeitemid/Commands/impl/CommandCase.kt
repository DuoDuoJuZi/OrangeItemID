package com.orangeitemid.Commands.impl

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.platform.util.asLangText
import taboolib.platform.util.sendLang

object CommandCase {

    // 默认大写
    @Volatile
    var isUpperCase = true
        private set

    @CommandBody
    val case = subCommand {
        execute<CommandSender> { sender, _, _ ->
            if (sender is Player) {
                isUpperCase = !isUpperCase
                val CurrentCase = if (isUpperCase) sender.asLangText("CurrentCase-Uppercase") else sender.asLangText("CurrentCase-Lowercase")

                sender.sendLang("CurrentCase-Toggle", CurrentCase)
            } else {

                sender.sendLang("Only-Player-Command")
            }
        }
    }
}