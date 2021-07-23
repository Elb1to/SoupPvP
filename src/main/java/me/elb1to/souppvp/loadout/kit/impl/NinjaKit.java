package me.elb1to.souppvp.loadout.kit.impl;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.loadout.ability.Ability;
import me.elb1to.souppvp.loadout.kit.Kit;
import me.elb1to.souppvp.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 4:40 PM
 */
public class NinjaKit extends Kit {

    private final Color color = Color.fromRGB(0);

    public NinjaKit() {
        super("Ninja",
            Material.NETHER_STAR,
            new String[]{
                "&7&oThrow ninja stars to blind",
                "&7&oyour enemies. You receive",
                "&7&oa ninja star back and",
                "&7&o10 dura-bility to your",
                "&7&oarmor per kill."
            },
            5000);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
            new ItemBuilder(Material.LEATHER_BOOTS).color(color).enchantment(Enchantment.DURABILITY, 20).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(Material.LEATHER_LEGGINGS).color(color).enchantment(Enchantment.DURABILITY, 20).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(Material.LEATHER_CHESTPLATE).color(color).enchantment(Enchantment.DURABILITY, 20).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(Material.LEATHER_HELMET).color(color).enchantment(Enchantment.DURABILITY, 20).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build()
        };
    }

    @Override
    public ItemStack getSword() {
        return new ItemBuilder(Material.IRON_SWORD).enchantment(Enchantment.DAMAGE_ALL, 1).enchantment(Enchantment.DURABILITY, 3).build();
    }

    @Override
    public Ability getAbility() {
        return SoupPvP.getInstance().getAbilityManager().getAbilityByName("Shuriken");
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
            new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1),
            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0)
        };
    }
}
