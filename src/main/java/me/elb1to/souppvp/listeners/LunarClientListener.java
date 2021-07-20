package me.elb1to.souppvp.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCCooldown;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import com.lunarclient.bukkitapi.object.MinimapStatus;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import static org.bukkit.Material.DIAMOND_SWORD;
import static org.bukkit.Material.ENDER_PEARL;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Infames
 * Date: 20/07/2021 @ 08:59
 */
public class LunarClientListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LunarClientAPI.getInstance().setMinimapStatus(player, MinimapStatus.FORCED_OFF);
        LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint("Spawn", player.getWorld().getSpawnLocation(), -1, true, true));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();
        Entity entity;
        if (attacker != null && (entity = event.getEntity()) instanceof Player) {
            Player attacked = (Player) entity;
            LunarClientAPI.getInstance().sendCooldown(attacked, new LCCooldown("Combat", 15, TimeUnit.SECONDS, DIAMOND_SWORD));
        }
    }

}
