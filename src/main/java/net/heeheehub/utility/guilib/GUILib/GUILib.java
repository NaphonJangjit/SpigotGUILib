package net.heeheehub.utility.guilib.GUILib;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GUILib extends JavaPlugin {
    
	public static String GUILIB_PREFIX = "&7[&aGUILib&7] &a";
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', GUILIB_PREFIX + "GUILib Enabled"));
		GUIListener listener = new GUIListener();
		GUI.setGUIListener(listener);
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', GUILIB_PREFIX + "GUILib Disabled"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("testgui")) {
			if(sender instanceof Player player) {
				if(!player.hasPermission("guilib.test")) {
					player.sendMessage("You don't have permission to do this.");
					return true;
				}
				
				GUI test = new GUI(3, "&aTest &bGUI").setItem(13, new ItemStack(Material.DIAMOND), p -> {
					Player clicker = (Player)p.getWhoClicked();
					clicker.getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
				});
				test.open(player);
			}
		}
		return true;
	}
	
}
