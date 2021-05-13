package me.elb1to.souppvp.kit.impl;

import me.elb1to.souppvp.kit.Ability;
import me.elb1to.souppvp.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:42 PM
 */
public class ProKit extends Kit {

    public ProKit() {
        super("Pro", Material.DIAMOND, new String[]{
            "&7&oDouble credits when",
            "&7&oyou kill another",
            "&7&oplayers."
        }, 250);
    }

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				new ItemStack(Material.IRON_HELMET),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_BOOTS),
		};
	}

	@Override
	public ItemStack getSword() {
		return new ItemStack(Material.DIAMOND_SWORD);
	}

	@Override
	public Ability getAbilityItem() {
		return null;
	}

	@Override
	public PotionEffect[] getPotionEffects() {
		return new PotionEffect[0];
	}
}
