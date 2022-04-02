package me.elb1to.souppvp.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketCooldown;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.events.CombatTagEvent;
import me.elb1to.souppvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.elb1to.souppvp.utils.ColorHelper.translate;
import static me.elb1to.souppvp.utils.PlayerUtil.resetHotbar;
import static me.elb1to.souppvp.utils.PlayerUtil.resetPlayer;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 3:07 PM
 */
public class PvPListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getDrops().removeIf(item -> item.getType() != Material.MUSHROOM_SOUP);

        Player victim = event.getEntity().getPlayer();
        User victimUser = SoupPvP.getInstance().getUserManager().getByUuid(victim.getUniqueId());
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            User killerUser = SoupPvP.getInstance().getUserManager().getByUuid(killer.getUniqueId());

            if (killerUser.getCurrentKitName().equals("CopyCat")) {
                SoupPvP.getInstance().getKitManager().getKitByName(victimUser.getCurrentKitName()).equipKit(killer);
            }
            event.getEntity().sendMessage(translate("&cYou have been killed by &a" + event.getEntity().getKiller().getName() + "&c."));
            event.getEntity().getKiller().sendMessage(translate("&bYou have killed &a" + victim.getName() + " &bfor &a" + (killerUser.getCurrentKitName().equals("Pro") ? 20 : 10) + " credits&b."));
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
            event.getEntity().sendMessage(translate("&cYou have died."));
        }

        victimUser.setDeaths(victimUser.getDeaths() + 1);

        Bukkit.getScheduler().runTaskLater(SoupPvP.getInstance(), () -> {
            victim.spigot().respawn();

            resetPlayer(victim);
            resetHotbar(victim);

            SoupPvP.getInstance().getCombatManager().setCombatSet(victim, false);
        }, 1L);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
            return;
        }

        Player victim = (Player) event.getEntity();
        Player attacker = (Player) event.getDamager();
        if (SoupPvP.getInstance().getSpawnController().getCuboid().isIn((victim)) && SoupPvP.getInstance().getSpawnController().getCuboid().isIn((attacker)) ||
            !SoupPvP.getInstance().getSpawnController().getCuboid().isIn((victim)) && SoupPvP.getInstance().getSpawnController().getCuboid().isIn((attacker)) ||
            SoupPvP.getInstance().getSpawnController().getCuboid().isIn((victim)) && !SoupPvP.getInstance().getSpawnController().getCuboid().isIn((attacker))) {

            event.setCancelled(true);
        }

        Bukkit.getPluginManager().callEvent(new CombatTagEvent(victim, attacker));
        SoupPvP.getInstance().getCombatManager().setCombatTime(victim, 16);
        SoupPvP.getInstance().getCombatManager().setCombatTime(attacker, 16);
        SoupPvP.getInstance().getCombatManager().setCombatSet(victim, true);
        SoupPvP.getInstance().getCombatManager().setCombatSet(attacker, true);
    }

    @EventHandler
    public void onCombatTag(CombatTagEvent event) {
        Player player = event.getPlayer();
        Player attacker = event.getAttacker();

        if (LunarClientAPI.getInstance().isRunningLunarClient(player)) {
            LunarClientAPI.getInstance().sendPacket(player, new LCPacketCooldown("Combat Cooldown", 16000, Material.DIAMOND_SWORD.getId()));
        }
        if (LunarClientAPI.getInstance().isRunningLunarClient(attacker)) {
            LunarClientAPI.getInstance().sendPacket(attacker, new LCPacketCooldown("Combat Cooldown", 16000, Material.DIAMOND_SWORD.getId()));
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (SoupPvP.getInstance().getSpawnController().getCuboid().isIn((Player) event.getEntity())) {
            event.setCancelled(true);
        }
    }
}
