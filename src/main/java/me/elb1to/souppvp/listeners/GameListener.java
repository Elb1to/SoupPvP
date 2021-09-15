package me.elb1to.souppvp.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.user.ui.kit.KitSelectionMenu;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.elb1to.souppvp.utils.PlayerUtil.*;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 1:28 PM
 */
public class GameListener implements Listener {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        resetPlayer(player);
        resetHotbar(player);

        if (this.plugin.getSpawnController().getSpawnLocation() != null) {
            LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint("SPAWN", this.plugin.getSpawnController().getSpawnLocation().toBukkitLocation(), Color.AQUA.asRGB(), true, true));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }

        User user = this.plugin.getUserManager().getByUuid(player.getUniqueId());
        if (user == null) return;

        if (event.getItem().equals(KIT_SELECTOR)) {
            new KitSelectionMenu().openMenu(player);
        } else if (event.getItem().equals(EVENT_HOSTING)) {
            player.sendMessage("Open Events Menu");
        } else if (event.getItem().equals(PLAYER_PERKS)) {
            player.sendMessage("Open Perks Menu");
        } else if (event.getItem().equals(PREVIOUS_KIT)) {
            this.plugin.getKitManager().getKitByName(user.getCurrentKitName()).equipKit(player);
        } else if (event.getItem().getType().equals(Material.SKULL_ITEM)) {
            sendStats(player, user);
        }
    }

    @EventHandler
    public void onSoupConsumption(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() == Material.MUSHROOM_SOUP && player.getHealth() < 19.5) {
            player.setHealth(Math.min(player.getHealth() + 7.0, 20.0));
            player.getItemInHand().setType(Material.BOWL);
            player.updateInventory();
        }
    }

    @EventHandler
    public void onBowlDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.BOWL) {
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onSpawnExit(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        User user = this.plugin.getUserManager().getByUuid(player.getUniqueId());
        if (user == null) {
            return;
        }
        if (!this.plugin.getSpawnController().getCuboid().isIn((player)) && (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ()) && player.getInventory().contains(KIT_SELECTOR)) {
            this.plugin.getKitManager().getKitByName(user.getCurrentKitName()).equipKit(player);
        }
    }
}
