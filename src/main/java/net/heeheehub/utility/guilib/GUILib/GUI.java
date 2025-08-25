package net.heeheehub.utility.guilib.GUILib;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.heeheehub.apihub.APIHub.utils.ColorFormat;
import net.md_5.bungee.api.ChatColor;

public class GUI {
	private static GUIListener listener;
	
	private final Inventory inventory;
	private final Map<Integer, Consumer<GUIParams>> handlers = new HashMap<>();
	private Consumer<GUIParams> onCloseCons = null;
	private final Map<Integer, Boolean> cancelled = new HashMap<Integer, Boolean>();
	
	static void setGUIListener(GUIListener listener) {
		GUI.listener = listener;
	}
	
	public GUI(int rows, String title) {
		this.inventory = Bukkit.createInventory(null, rows * 9, ColorFormat.format2(title));
	}
	
	public GUI(String title, InventoryType type) {
		this.inventory = Bukkit.createInventory(null, type, ColorFormat.format2(title));
	}
	
	public GUI setItem(int slot, ItemStack item, Consumer<GUIParams> event) {
		inventory.setItem(slot, item);
		return setAction(slot, event);
	}
	
	public void setCancelled(int slot, boolean b) {
		cancelled.put(slot, b);
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
		return setAction(slot, handlers.getOrDefault(slot, _ -> {}));
	}
	
	public GUI setItems(ItemStack item, int... slots) {
	    for (int slot : slots) {
	        inventory.setItem(slot, item);
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
	
	public GUI onClose(Consumer<GUIParams> p) {
		this.onCloseCons = p;
		return this;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Consumer<GUIParams> getOnCloseCons() {
		return onCloseCons;
	}
	
	public boolean isCancelled(int slot) {
		return cancelled.getOrDefault(slot, true);
	}
	
	
	
}
