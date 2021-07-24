package me.elb1to.souppvp.loadout.ability;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:26 PM
 */
@Getter
public abstract class Ability {

    private final String name;
    private final ItemStack item;

    public Ability(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

    public abstract long getCooldown();

    public abstract AbilityCallable getCallable();
}
