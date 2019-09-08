package com.dutchjelly.util;


import com.dutchjelly.compatibility.AColor;
import com.dutchjelly.compatibility.Adapter;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIButtons {

	public static ItemStack back;
	public static ItemStack filling;
	public static ItemStack next;
	public static ItemStack previous;
	
	public static void init(){
		//TODO make this configurable
		back = makeItem("&7Back to previous page", Material.GLOWSTONE_DUST, true);
		filling = makeItem("", Adapter.GetStainedGlassPane(AColor.GRAY), false);
		next = makeItem("&2Next", Adapter.GetStainedGlassPane(AColor.LIGHT_BLUE), true);
		previous = makeItem("&2Previous", Adapter.GetStainedGlassPane(AColor.LIGHT_BLUE), true);
	}
	
	private static ItemMeta setBasicMeta(ItemMeta meta, boolean enchant){
		if(enchant){
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		return meta;
	}

	private static ItemStack makeItem(String name, Material type, boolean enchant){
		ItemStack item = new ItemStack(type);
		return makeItem(name, item, enchant);
	}

	private static ItemStack makeItem(String name, ItemStack item, boolean enchant){
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(setBasicMeta(meta, enchant));
		return item;
	}

}
