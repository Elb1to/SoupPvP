package me.elb1to.souppvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerPortalEvent;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 5:02 PM
 */
public class ServerListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onFoodConsumption(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onPortalEnter(PlayerPortalEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}
}
