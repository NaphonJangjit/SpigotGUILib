package net.heeheehub.utility.guilib.GUILib;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIParams {
	private final ClickType click;
    private ItemStack current = null;
	private HumanEntity whoClicked;
	private GUI instance;
    
    public GUIParams(ClickType click, ItemStack current, HumanEntity whoCliked, GUI instance) {
    	this.click = click;
    	this.current = current;
    	this.whoClicked = whoCliked;
    	this.instance = instance;
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
    
    public GUI getInstance() {
		return instance;
	}
    
}
