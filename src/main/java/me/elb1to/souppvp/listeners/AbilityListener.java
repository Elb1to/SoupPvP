package me.elb1to.souppvp.listeners;

import me.elb1to.souppvp.SoupPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 4:38 PM
 */
public class AbilityListener implements Listener {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }

        if (event.getAction().name().startsWith("RIGHT_")) {
            if (event.getItem().isSimilar(this.plugin.getAbilityManager().getAbilityByName("Shuriken").getItem())) {
                this.plugin.getAbilityManager().getAbilityByName("Shuriken").getCallable().execute(player);
            } else if (event.getItem().isSimilar(this.plugin.getAbilityManager().getAbilityByName("Phantom").getItem())) {
                player.sendMessage("Do Phantom temp flight ability");
            }
        }
    }
}
