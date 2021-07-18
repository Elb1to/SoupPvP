package me.elb1to.souppvp.listeners;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.user.ui.kit.KitSelectionMenu;
import me.elb1to.souppvp.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getItem() == null) {
            return;
        }

        if (event.getItem().equals(KIT_SELECTOR)) {
            new KitSelectionMenu().openMenu(player);
        } else if (event.getItem().equals(EVENT_HOSTING)) {
            player.sendMessage("Open Events Menu");
        } else if (event.getItem().equals(PLAYER_PERKS)) {
            player.sendMessage("Open Perks Menu");
        } else if (event.getItem().equals(PREVIOUS_KIT)) {
            player.sendMessage("Choose previous kit");
        } else if (event.getItem().getType().equals(Material.SKULL_ITEM)) {
            player.sendMessage("Open Stats Menu");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        Player player = event.getEntity();
        Bukkit.getScheduler().runTaskLater(SoupPvP.getInstance(), () -> player.spigot().respawn(), 1L);

        if (event.getEntity().getKiller() != null) {
            User kUser = this.plugin.getUserManager().getByUuid(event.getEntity().getKiller().getUniqueId());
            User dUser = this.plugin.getUserManager().getByUuid(player.getUniqueId());

            event.getEntity().sendMessage(CC.translate("&cYou have been killed by &a" + event.getEntity().getKiller().getName() + "&c."));
            event.getEntity().getKiller().sendMessage(CC.translate("&bYou have killed &a" + player.getName() + " &bfor &a" + 10 + " credits&b."));
            kUser.setCredits(kUser.getCredits() + 10);

            kUser.setKills(kUser.getKills() + 1);
            kUser.setCurrentKillstreak(kUser.getCurrentKillstreak() + 1);
            dUser.setCurrentKillstreak(0);
            if (kUser.getCurrentKillstreak() > kUser.getHighestKillstreak()) {
                kUser.setHighestKillstreak(kUser.getCurrentKillstreak());
            }
            dUser.setDeaths(dUser.getDeaths() + 1);
        } else {
            event.getEntity().sendMessage(CC.translate("&cYou have died."));

            User dUser = this.plugin.getUserManager().getByUuid(player.getUniqueId());
            dUser.setDeaths(dUser.getDeaths() + 1);
        }

        resetPlayer(player);
        resetHotbar(player);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
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

    private boolean inCuboid(Player player) {
        return this.plugin.getSpawnController().getCuboid().isIn(player);
    }
}
