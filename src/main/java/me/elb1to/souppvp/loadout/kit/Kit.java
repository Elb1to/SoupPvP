package me.elb1to.souppvp.loadout.kit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.elb1to.souppvp.loadout.ability.Ability;
import me.elb1to.souppvp.utils.ColorHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 1:54 PM
 */
@Getter
@RequiredArgsConstructor
public abstract class Kit {

	private final String name;
	private final Material icon;
	private final String[] desc;
	private final int price;

	public void equipKit(Player player) {
		player.getInventory().clear();

		Arrays.stream(this.getPotionEffects()).forEach(player::addPotionEffect);
		player.getInventory().setArmorContents(this.getArmor());
		player.getInventory().setItem(0, getSword());
		if (getAbility() != null) player.getInventory().setItem(1, getAbility().getItem());
		this.giveSoups(player);

		player.updateInventory();
        player.sendMessage(ColorHelper.translate("&eYou have chosen the &d" + this.getName() + "&e kit."));
	}

	public abstract ItemStack[] getArmor();

	public abstract ItemStack getSword();

	public abstract Ability getAbility();

    public abstract PotionEffect[] getPotionEffects();

	private void giveSoups(Player player) {
        Arrays.stream(player.getInventory().getContents()).filter(Objects::isNull).map(itemStack -> new ItemStack(Material.MUSHROOM_SOUP)).forEach(soup -> player.getInventory().addItem(soup));
	}
}
