package me.elb1to.souppvp.listeners.combat;

import lombok.Getter;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.ColorHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Infames
 * Date: 23/07/2021 @ 20:08
 */
@Getter
public class CombatManager extends BukkitRunnable {

    private final Set<Player> combatSet = new HashSet<>();
    private final Set<Player> spawnSet = new HashSet<>();
    private final Map<Player, Integer> spawnMap = new HashMap<>();
    private final Map<Player, Integer> timeMap = new HashMap<>();
    private final HashMap<Player, Player> targetMap = new HashMap<>();

    private final SoupPvP plugin = SoupPvP.getInstance();

    private int count = 0;

    public void setCombatSet(Player player, boolean b) {
        if (b) {
            combatSet.add(player);
            timeMap.put(player, 16);
        } else {
            combatSet.remove(player);
            timeMap.remove(player);
        }
    }

    public void setCombatTime(Player player, int time) {
        timeMap.remove(player);
        timeMap.put(player, time);
    }

    public void setSpawnSet(Player player, boolean b) {
        if (b) {
            spawnSet.add(player);
            spawnMap.put(player, 5);
        } else {
            spawnSet.remove(player);
            spawnMap.remove(player);
        }
    }

    public boolean isCombat(Player player) {
        return combatSet.contains(player);
    }

    public boolean isSpawn(Player player) {
        return spawnSet.contains(player);
    }

    public int getCombatTime(Player player) {
        return timeMap.get(player);
    }

    @Override
    public void run() {
        count++;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (plugin.getCombatManager().isCombat(player)) {
                int count = plugin.getCombatManager().getCombatTime(player);
                --count;
                plugin.getCombatManager().setCombatTime(player, count);
                if (count == 0) {
                    player.sendMessage(ColorHelper.translate("&aYou are no longer in combat"));
                    plugin.getCombatManager().getCombatSet().remove(player);
                    plugin.getCombatManager().getTimeMap().remove(player);
                }
            }
            if (count == 160) {
                count = 0;
            }
        }
    }
}

