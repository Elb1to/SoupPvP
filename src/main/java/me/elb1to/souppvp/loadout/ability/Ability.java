package me.elb1to.souppvp.loadout.ability;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:26 PM
 */
@Getter
@RequiredArgsConstructor
public abstract class Ability {

    private final String name;
    private final ItemStack item;

    public abstract long getCooldown();

    public abstract AbilityCallable getCallable();
}
