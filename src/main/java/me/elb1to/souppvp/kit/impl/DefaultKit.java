package me.elb1to.souppvp.kit.impl;

import me.elb1to.souppvp.kit.Ability;
import me.elb1to.souppvp.kit.Kit;
import me.elb1to.souppvp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:00 PM
 */
public class DefaultKit extends Kit {

    public DefaultKit() {
        super("Default", Material.DIAMOND_SWORD, new String[]{
            "&7&oDefault PvP kit."
        }, 0);
    }

	@Override
	public ItemStack getSword() {
		return new ItemBuilder(Material.DIAMOND_SWORD)
				.enchantment(Enchantment.DAMAGE_ALL, 1)
				.enchantment(Enchantment.DURABILITY, 3)
				.build()
				;
	}

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				new ItemStack(Material.IRON_BOOTS),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_PROJECTILE).build()
		};
	}

	@Override
	public Ability getAbilityItem() {
		return null;
	}

	@Override
	public PotionEffect[] getPotionEffects() {
		return new PotionEffect[]{
				new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0)
		};
	}
}
