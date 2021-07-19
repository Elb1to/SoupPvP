package me.elb1to.souppvp.kit;

import lombok.Getter;
import me.elb1to.souppvp.utils.ColorHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 1:54 PM
 */
@Getter
public abstract class Kit {

	private final String name;
	private final Material icon;
	private final String[] desc;
	private final int price;

	public Kit(String name, Material icon, String[] desc, int price) {
		this.name = name;
        this.icon = icon;
        this.desc = desc;
		this.price = price;
	}

	public void equipKit(Player player) {
		player.getInventory().clear();

		Arrays.stream(this.getPotionEffects()).forEach(player::addPotionEffect);
		player.getInventory().setArmorContents(this.getArmor());
		player.getInventory().setItem(0, getSword());
		this.giveSoups(player);

		player.updateInventory();
        player.sendMessage(ColorHelper.translate("&eYou have chosen the &d" + this.getName() + "&e kit."));
	}

	public abstract PotionEffect[] getPotionEffects();

	public abstract ItemStack[] getArmor();

	public abstract ItemStack getSword();

	public abstract Ability getAbilityItem();

	private void giveSoups(Player player) {
		final ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		for (ItemStack inv : player.getInventory().getContents()) {
			if (inv == null) {
				player.getInventory().addItem(soup);
			}
		}
	}
}
