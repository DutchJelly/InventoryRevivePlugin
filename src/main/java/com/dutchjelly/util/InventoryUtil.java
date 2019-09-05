package com.dutchjelly.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class InventoryUtil {


    public static Inventory deserialize(Map<String, Object> serialized){
        PlayerInventory inv = new PlayerInventory() {
            @Override
            public ItemStack[] getArmorContents() {
                return new ItemStack[0];
            }

            @Override
            public ItemStack[] getExtraContents() {
                return new ItemStack[0];
            }

            @Override
            public ItemStack getHelmet() {
                return null;
            }

            @Override
            public ItemStack getChestplate() {
                return null;
            }

            @Override
            public ItemStack getLeggings() {
                return null;
            }

            @Override
            public ItemStack getBoots() {
                return null;
            }

            @Override
            public void setItem(int i, ItemStack itemStack) {

            }

            @Override
            public void setArmorContents(ItemStack[] itemStacks) {

            }

            @Override
            public void setExtraContents(ItemStack[] itemStacks) {

            }

            @Override
            public void setHelmet(ItemStack itemStack) {

            }

            @Override
            public void setChestplate(ItemStack itemStack) {

            }

            @Override
            public void setLeggings(ItemStack itemStack) {

            }

            @Override
            public void setBoots(ItemStack itemStack) {

            }

            @Override
            public ItemStack getItemInMainHand() {
                return null;
            }

            @Override
            public void setItemInMainHand(ItemStack itemStack) {

            }

            @Override
            public ItemStack getItemInOffHand() {
                return null;
            }

            @Override
            public void setItemInOffHand(ItemStack itemStack) {

            }

            @Override
            public ItemStack getItemInHand() {
                return null;
            }

            @Override
            public void setItemInHand(ItemStack itemStack) {

            }

            @Override
            public int getHeldItemSlot() {
                return 0;
            }

            @Override
            public void setHeldItemSlot(int i) {

            }

            @Override
            public HumanEntity getHolder() {
                return null;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getMaxStackSize() {
                return 0;
            }

            @Override
            public void setMaxStackSize(int i) {

            }

            @Override
            public ItemStack getItem(int i) {
                return null;
            }

            @Override
            public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
                return null;
            }

            @Override
            public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
                return null;
            }

            @Override
            public ItemStack[] getContents() {
                return new ItemStack[0];
            }

            @Override
            public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {

            }

            @Override
            public ItemStack[] getStorageContents() {
                return new ItemStack[0];
            }

            @Override
            public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {

            }

            @Override
            public boolean contains(Material material) throws IllegalArgumentException {
                return false;
            }

            @Override
            public boolean contains(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean contains(Material material, int i) throws IllegalArgumentException {
                return false;
            }

            @Override
            public boolean contains(ItemStack itemStack, int i) {
                return false;
            }

            @Override
            public boolean containsAtLeast(ItemStack itemStack, int i) {
                return false;
            }

            @Override
            public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
                return null;
            }

            @Override
            public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
                return null;
            }

            @Override
            public int first(Material material) throws IllegalArgumentException {
                return 0;
            }

            @Override
            public int first(ItemStack itemStack) {
                return 0;
            }

            @Override
            public int firstEmpty() {
                return 0;
            }

            @Override
            public void remove(Material material) throws IllegalArgumentException {

            }

            @Override
            public void remove(ItemStack itemStack) {

            }

            @Override
            public void clear(int i) {

            }

            @Override
            public void clear() {

            }

            @Override
            public List<HumanEntity> getViewers() {
                return null;
            }

            @Override
            public InventoryType getType() {
                return null;
            }

            @Override
            public ListIterator<ItemStack> iterator() {
                return null;
            }

            @Override
            public ListIterator<ItemStack> iterator(int i) {
                return null;
            }

            @Override
            public Location getLocation() {
                return null;
            }
        };
        return null;
    }


    public static boolean IsEmpty(PlayerInventory inventory){
        return IsEmpty(inventory.getArmorContents())
                && IsEmpty(inventory.getExtraContents())
                && IsEmpty(inventory.getContents());
    }

    public static boolean IsEmpty(ItemStack[] items){
        for(ItemStack item : items){
            if(item != null && !item.getType().equals(Material.AIR))
                return false;
        }
        return true;
    }
}
