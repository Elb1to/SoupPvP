package me.elb1to.souppvp.loadout.ability.impl;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.loadout.ability.Ability;
import me.elb1to.souppvp.loadout.ability.AbilityCallable;
import me.elb1to.souppvp.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 3:48 PM
 */
public class ShurikenAbility extends Ability {

    public ShurikenAbility() {
        super("Shuriken",
            new ItemBuilder(Material.NETHER_STAR).name("&9Shuriken").lore(Arrays.asList("&7Right click to throw a Shuriken", "&7that makes people go blind.")).build()
        );
    }

    @Override
    public long getCooldown() {
        return 0L;
    }

    @Override // This ability is unfinished, so don't fuckin spam my dms.
    public AbilityCallable getCallable() {
        return player -> {
            ArmorStand shuriken = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            shuriken.setItemInHand(getItem());
            shuriken.setVisible(false);
            shuriken.setGravity(false);
            shuriken.setMarker(true);

            player.getItemInHand().setAmount(0);

            Location location = player.getLocation().add(player.getLocation().getDirection().multiply(10));
            Vector vector = location.subtract(player.getLocation()).toVector();

            new BukkitRunnable() {
                final int dist = 15;
                int distTraveled = 0;

                @Override
                public void run() {
                    shuriken.teleport(shuriken.getLocation().add(vector.normalize()));
                    if (distTraveled > dist) {
                        shuriken.remove();
                        cancel();
                    }

                    distTraveled++;
                }
            }.runTaskTimer(SoupPvP.getInstance(), 0L, 1L);
        };
    }
}
