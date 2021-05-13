package me.elb1to.souppvp.listeners;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 11:29 AM
 */
public class UserListener implements Listener {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Player player = Bukkit.getPlayer(event.getUniqueId());
        if (player != null && player.isOnline()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(CC.translate("&cYou tried to login too quickly after disconnecting.\n&cTry again in a few seconds."));

            this.plugin.getServer().getScheduler().runTask(this.plugin, () -> player.kickPlayer(CC.translate("&cDuplicate login kick")));
            return;
        }

        User user = this.plugin.getUserManager().getOrCreate(event.getUniqueId());
        user.save();
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());
        if (user == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
            return;
        }

        if (!user.isLoaded()) {
            user.save();
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
        }
    }

    private void handledSaveDate(Player player) {
        User user = this.plugin.getUserManager().getOrCreate(player.getUniqueId());
        if (user != null) {
            user.delete();
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        handledSaveDate(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) {
        handledSaveDate(event.getPlayer());
    }
}
