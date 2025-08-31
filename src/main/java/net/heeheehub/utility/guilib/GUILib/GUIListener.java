package net.heeheehub.utility.guilib.GUILib;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;

class GUIListener implements Listener{
	
	private final Map<UUID, GUI> openGUIs = new HashMap<>();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		HumanEntity he = event.getWhoClicked();
		Player player = (Player) he;
		GUI gui = openGUIs.get(player.getUniqueId());
		
		if(gui != null && event.getClickedInventory() != null && event.getClickedInventory().equals(gui.getInventory())) {
			event.setCancelled(gui.isCancelled(event.getSlot()));
			gui.handleClick(event.getSlot(), new GUIParams(event.getClick(), event.getCurrentItem(), he, gui, event));
		}
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
	    HumanEntity he = event.getWhoClicked();
	    if (!(he instanceof Player)) return;

	    Player player = (Player) he;
	    GUI gui = openGUIs.get(player.getUniqueId());

	    if (gui != null && event.getInventory().equals(gui.getInventory())) {
	        for (int slot : event.getRawSlots()) {
	            if (slot < gui.getInventory().getSize()) {
	                if (gui.isCancelled(slot)) {
	                    event.setCancelled(true);
	                    break;
	                }
	            }
	        }
	    }
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if(openGUIs.containsKey(event.getPlayer().getUniqueId())) {
			GUI g = openGUIs.get(event.getPlayer().getUniqueId());
			if(g.getOnCloseCons() != null) {
				g.getOnCloseCons().accept(new GUIParams(null, null, event.getPlayer(), g, null));
			}
		}
		openGUIs.remove(event.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		openGUIs.remove(event.getPlayer().getUniqueId()); //for sure
	}
	
	public void track(Player player, GUI gui) {
		openGUIs.put(player.getUniqueId(), gui);
	}
	
}
