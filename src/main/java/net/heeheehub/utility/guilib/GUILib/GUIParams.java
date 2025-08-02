package net.heeheehub.utility.guilib.GUILib;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GUIParams {
	private final ClickType click;
    private ItemStack current = null;
	private HumanEntity whoClicked;
    
    public GUIParams(ClickType click, ItemStack current, HumanEntity whoCliked) {
    	this.click = click;
    	this.current = current;
    	this.whoClicked = whoCliked;
    }
    
    public ClickType getClick() {
		return click;
	}
    
    public ItemStack getCurrentItem() {
		return current;
	}
    
    public HumanEntity getWhoClicked() {
		return whoClicked;
	}
    
}
