package me.elb1to.souppvp.listeners.combat;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCCooldown;
import me.elb1to.souppvp.SoupPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.concurrent.TimeUnit;

import static org.bukkit.Material.DIAMOND_SWORD;

/**
 * Created by Infames
 * Date: 23/07/2021 @ 20:04
 */
public class CombatListener implements Listener {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();
            CombatTagEvent combatTagEvent = new CombatTagEvent(player, attacker);
            Bukkit.getPluginManager().callEvent(combatTagEvent);
            plugin.getCombatManager().setCombatTime(player, 15);
            plugin.getCombatManager().setCombatTime(attacker, 15);
            plugin.getCombatManager().setCombatSet(player, true);
            plugin.getCombatManager().setCombatSet(attacker, true);
        }
    }


    @EventHandler
    public void onCombatTag(CombatTagEvent event) {
        Player player = event.getPlayer();
        Player attacker = event.getAttacker();
        if (LunarClientAPI.getInstance().isRunningLunarClient(player)) {
            LunarClientAPI.getInstance().sendCooldown(player, new LCCooldown("Combat", 15, TimeUnit.SECONDS, DIAMOND_SWORD));
        }
        if (LunarClientAPI.getInstance().isRunningLunarClient(attacker)) {
            LunarClientAPI.getInstance().sendCooldown(attacker, new LCCooldown("Combat", 15, TimeUnit.SECONDS, DIAMOND_SWORD));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        if (plugin.getCombatManager().isSpawn(player) && (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ())) {
            plugin.getCombatManager().setSpawnSet(player, false);
        }
    }
}

