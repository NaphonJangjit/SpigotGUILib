package net.heeheehub.utility.guilib.GUILib;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GUILib extends JavaPlugin {
    
	public static String GUILIB_PREFIX = "&7[&aGUILib&7] &a";
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', GUILIB_PREFIX + "GUILib Enabled"));
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', GUILIB_PREFIX + "GUILib Disabled"));
	}
	
}
