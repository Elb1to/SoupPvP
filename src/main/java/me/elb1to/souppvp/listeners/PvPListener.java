package me.elb1to.souppvp.listeners;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.ColorHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.elb1to.souppvp.utils.PlayerUtil.resetHotbar;
import static me.elb1to.souppvp.utils.PlayerUtil.resetPlayer;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 3:07 PM
 */
public class PvPListener implements Listener {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getDrops().removeIf(item -> item.getType() != Material.MUSHROOM_SOUP);

        Player victim = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;

        User victimUser = SoupPvP.getInstance().getUserManager().getByUuid(victim.getUniqueId());
        User killerUser = SoupPvP.getInstance().getUserManager().getByUuid(killer.getUniqueId());

        if (killerUser.getCurrentKitName().equals("CopyCat")) {
            this.plugin.getKitManager().getKitByName(victimUser.getCurrentKitName()).equipKit(killer);
        }

        if (event.getEntity().getKiller() != null) {
            event.getEntity().sendMessage(ColorHelper.translate("&cYou have been killed by &a" + event.getEntity().getKiller().getName() + "&c."));
            event.getEntity().getKiller().sendMessage(ColorHelper.translate("&bYou have killed &a" + victim.getName() + " &bfor &a" + (killerUser.getCurrentKitName().equals("Pro") ? 20 : 10) + " credits&b."));
            if (killerUser.getCurrentKitName().equals("Pro")) {
                killerUser.setCredits(killerUser.getCredits() + 20);
            } else {
                killerUser.setCredits(killerUser.getCredits() + 10);
            }

            killerUser.setKills(killerUser.getKills() + 1);
            killerUser.setCurrentKillstreak(killerUser.getCurrentKillstreak() + 1);
            victimUser.setCurrentKillstreak(0);
            if (killerUser.getCurrentKillstreak() > killerUser.getHighestKillstreak()) {
                killerUser.setHighestKillstreak(killerUser.getCurrentKillstreak());
            }
        } else {
            event.getEntity().sendMessage(ColorHelper.translate("&cYou have died."));
        }

        victimUser.setDeaths(victimUser.getDeaths() + 1);

        Bukkit.getScheduler().runTaskLater(SoupPvP.getInstance(), () -> {
            victim.spigot().respawn();

            resetPlayer(victim);
            resetHotbar(victim);
        }, 1L);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player victim = (Player) event.getEntity();
        Player attacker = (Player) event.getDamager();

        if (inCuboid(victim) && inCuboid(attacker) || !inCuboid(victim) && inCuboid(attacker) || inCuboid(victim) && !inCuboid(attacker)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (inCuboid((Player) event.getEntity())) {
            event.setCancelled(true);
        }
    }

    private boolean inCuboid(Player player) {
        return this.plugin.getSpawnController().getCuboid().isIn(player);
    }
}
