package com.orangeitemid.Commands

import com.orangeitemid.Commands.impl.CommandCase
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.*
import taboolib.common.platform.command.CommandHeader
import taboolib.module.chat.TellrawJson
import taboolib.platform.util.asLangText
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.function.adaptCommandSender
import java.util.*

import com.orangeitemid.Commands.impl.CommandGet
import com.orangeitemid.Commands.impl.CommandReload
import com.orangeitemid.OrangeItemID

@CommandHeader(name = "id", aliases = ["itemid", "orangeitemid", "oid"], permission = "id.use", description = "获取和操作玩家的ID")
object Commands {

    @CommandBody
    val main = mainCommand {
        execute<CommandSender> { sender, _, _ ->
            generateMainHelper(sender)
        }
    }
    @AppearHelper
    @CommandBody(permission = "orangeitemid.get" )
    val get = CommandGet.get

    @AppearHelper
    @CommandBody(permission = "orangeitemid.get" )
    val case = CommandCase.case

    @AppearHelper
    @CommandBody(permission = "orangeitemid.reload" )
    val reload = CommandReload.reload


    // 处理help
    @CommandBody(optional = true)
    val help = subCommand {
        execute<CommandSender> { sender, _, _ ->
            generateMainHelper(sender)
        }
    }

    fun generateMainHelper(sender: CommandSender) {
        val proxySender = adaptCommandSender(sender)
        proxySender.sendMessage("")
        TellrawJson()
                .append("  ").append("§6OrangeItemID").append(" §7${OrangeItemID.plugin.description.version}")
                .hoverText("§7轻松获取你手上物品的ID")
                .hoverText("""
            """.trimIndent()).sendTo(proxySender)
        proxySender.sendMessage("")
        TellrawJson()
                .append("  §7命令: ").append("§f/oid §8[...]")
                .hoverText("§f/oid §8[...]")
                .suggestCommand("/oid ")
                .sendTo(proxySender)
        proxySender.sendMessage("  §7参数:")

        javaClass.declaredFields.forEach {
            if (!it.isAnnotationPresent(AppearHelper::class.java)) return@forEach
            val name = it.name
            val regularName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val desc = sender.asLangText("Command-$regularName-Description")

            TellrawJson()
                    .append("    §8- ").append("§f$name")
                    .hoverText("§f/oid $name §8- §7$desc")
                    .suggestCommand("/oid $name ")
                    .sendTo(proxySender)
            proxySender.sendMessage("      §7$desc")
        }
        proxySender.sendMessage("")
    }
}
