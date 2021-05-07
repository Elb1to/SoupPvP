package me.elb1to.souppvp.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder implements Listener {

	private final ItemStack is;

	public ItemBuilder(final Material mat) {
		is = new ItemStack(mat);
	}

	public ItemBuilder(final ItemStack is) {
		this.is = is;
	}

	public ItemBuilder amount(final int amount) {
		is.setAmount(amount);

		return this;
	}

	public ItemBuilder name(final String name) {
		final ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(final String name) {
		final ItemMeta meta = is.getItemMeta();
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<>();
		}

		lore.add(name);
		meta.setLore(lore);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(final List<String> lore) {
		List<String> toSet = new ArrayList<>();
		ItemMeta meta = is.getItemMeta();

		for (String string : lore) {
			toSet.add(ChatColor.translateAlternateColorCodes('&', string));
		}

		meta.setLore(toSet);
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder durability(final int durability) {
		is.setDurability((short) durability);

		return this;
	}

	public ItemBuilder head(String url) {
		SkullMeta headMeta = (SkullMeta) is.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField;

		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		is.setItemMeta(headMeta);
		return this;
	}

	public ItemBuilder owner(String owner) {
		if (this.is.getType() == Material.SKULL_ITEM) {
			SkullMeta meta = (SkullMeta) this.is.getItemMeta();
			meta.setOwner(owner);
			this.is.setItemMeta(meta);
			return this;
		}

		throw new IllegalArgumentException("setOwner() only applicable for Skull Item");
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder data(final int data) {
		is.setData(new MaterialData(is.getType(), (byte) data));

		return this;
	}

	public ItemBuilder enchantment(final Enchantment enchantment, final int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder enchantment(final Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder hideFlags() {
		final ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder type(final Material material) {
		is.setType(material);
		return this;
	}

	public ItemBuilder clearLore() {
		final ItemMeta meta = is.getItemMeta();
		meta.setLore(new ArrayList<>());
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder clearEnchantments() {
		for (final Enchantment e : is.getEnchantments().keySet()) {
			is.removeEnchantment(e);
		}

		return this;
	}

	public ItemBuilder color(Color color) {
		if (is.getType() == Material.LEATHER_BOOTS || is.getType() == Material.LEATHER_CHESTPLATE
				|| is.getType() == Material.LEATHER_HELMET || is.getType() == Material.LEATHER_LEGGINGS) {
			LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);

			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor!");
		}
	}

	public ItemStack build() {
		return is;
	}
}