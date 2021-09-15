package me.elb1to.souppvp.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import com.lunarclient.bukkitapi.object.MinimapStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
}
