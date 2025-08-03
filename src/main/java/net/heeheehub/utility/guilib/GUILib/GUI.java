package net.heeheehub.utility.guilib.GUILib;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class GUI {
	private static GUIListener listener;
	
	private final Inventory inventory;
	private final Map<Integer, Consumer<GUIParams>> handlers = new HashMap<>();
	
	
	static void setGUIListener(GUIListener listener) {
		GUI.listener = listener;
	}
	
	public GUI(int rows, String title) {
		this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));
	}
	
	public GUI setItem(int slot, ItemStack item, Consumer<GUIParams> event) {
		inventory.setItem(slot, item);
		return setAction(slot, event);
	}
	
	public GUI setAction(int slot, Consumer<GUIParams> event) {
		handlers.put(slot, event);
		return this;
	}
	
	public GUI setItems(ItemStack item, Consumer<GUIParams> event, int... slots) {
	    for (int slot : slots) {
	        inventory.setItem(slot, item);
	        handlers.put(slot, event);
	    }
	    return this;
	}
	
	public GUI setActions(Consumer<GUIParams> event, int... slots) {
	    for (int slot : slots) {
	        handlers.put(slot, event);
	    }
	    return this;
	}
	
	public GUI setItem(int slot, ItemStack item) {
		inventory.setItem(slot, item);
		return setAction(slot, _ -> {});
	}
	
	public GUI setItems(ItemStack item, int... slots) {
	    for (int slot : slots) {
	        inventory.setItem(slot, item);
	        handlers.put(slot, _ -> {});
	    }
	    return this;
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
		listener.track(player, this);
	}
	
	void handleClick(int slot, GUIParams params) {
		handlers.getOrDefault(slot, _ -> {}).accept(params);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	
}
