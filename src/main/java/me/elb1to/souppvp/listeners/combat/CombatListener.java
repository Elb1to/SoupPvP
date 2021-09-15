package me.elb1to.souppvp.listeners.combat;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketCooldown;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.events.CombatTagEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

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

            Bukkit.getPluginManager().callEvent(new CombatTagEvent(player, attacker));
            this.plugin.getCombatManager().setCombatTime(player, 15);
            this.plugin.getCombatManager().setCombatTime(attacker, 15);
            this.plugin.getCombatManager().setCombatSet(player, true);
            this.plugin.getCombatManager().setCombatSet(attacker, true);
        }
    }

    @EventHandler
    public void onCombatTag(CombatTagEvent event) {
        Player player = event.getPlayer();
        Player attacker = event.getAttacker();

        if (LunarClientAPI.getInstance().isRunningLunarClient(player)) {
            LunarClientAPI.getInstance().sendPacket(player, new LCPacketCooldown("Combat Cooldown", 15000, 218));
        }
        if (LunarClientAPI.getInstance().isRunningLunarClient(attacker)) {
            LunarClientAPI.getInstance().sendPacket(attacker, new LCPacketCooldown("Combat Cooldown", 15000, 218));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (this.plugin.getCombatManager().isSpawn(player) && (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ())) {
            this.plugin.getCombatManager().setSpawnSet(player, false);
        }
    }
}

