package me.elb1to.souppvp.listeners.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Infames
 * Date: 23/07/2021 @ 20:07
 */

public class CombatTagEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player attacker;

    public CombatTagEvent(Player player, Player attacker) {
        super(player);
        this.attacker = attacker;
    }

    public Player getAttacker() {
        return attacker;
    }

    public HandlerList getHandlers(){
        return handlers;
    }
}

