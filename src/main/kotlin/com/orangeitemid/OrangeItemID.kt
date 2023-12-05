package com.orangeitemid

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.console
import org.bukkit.Bukkit
import taboolib.module.lang.sendLang
import taboolib.platform.BukkitPlugin

object OrangeItemID : Plugin() {

    val plugin by lazy { BukkitPlugin.getInstance() }

    override fun onEnable() {
        console().sendLang("Plugin-onEnable", plugin.description.version, Bukkit.getVersion())
    }

    override fun onDisable() {
        console().sendLang("Plugin-onDisable", plugin.description.version, Bukkit.getVersion())
    }
}