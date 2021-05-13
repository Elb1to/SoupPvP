package me.elb1to.souppvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:26 PM
 */
public abstract class Ability {

	public abstract String getAbilityName();

	public abstract String[] getAbilityDescription();

	public abstract ItemStack getAbilityItem();

	public abstract long getAbilityCooldown();

	public abstract AbilityCallable getAbilityCallable();

	public interface AbilityCallable {
		void execute(Player player);
	}
}
