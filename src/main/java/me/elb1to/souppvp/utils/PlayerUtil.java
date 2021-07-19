package me.elb1to.souppvp.utils;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 4:43 PM
 */
public class PlayerUtil {

    public static final ItemStack KIT_SELECTOR = new ItemBuilder(Material.ENCHANTED_BOOK).name("&aKit Selector").build();
    public static final ItemStack PREVIOUS_KIT = new ItemBuilder(Material.WATCH).name("&aSelect Previous Kit").build();
    public static final ItemStack PLAYER_PERKS = new ItemBuilder(Material.CHEST).name("&aPerks").build();
    public static final ItemStack EVENT_HOSTING = new ItemBuilder(Material.EMERALD).name("&aEvents").build();

    public static void resetPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);

        player.closeInventory();
        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setArmorContents(null);
        player.updateInventory();

        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setSaturation(12.8F);
        player.setMaximumNoDamageTicks(20);
        player.setFireTicks(0);
        player.setFallDistance(0.0F);

        player.setLevel(0);
        player.setExp(0.0F);

        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.2F);
        player.setAllowFlight(false);

        player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);
        ((CraftPlayer) player).getHandle().getDataWatcher().watch(9, (byte) 0);

        player.teleport(SoupPvP.getInstance().getSpawnController().getSpawnLocation().toBukkitLocation());
    }

    public static void resetHotbar(Player player) {
        player.getInventory().setItem(0, KIT_SELECTOR);
        player.getInventory().setItem(1, PREVIOUS_KIT);
        player.getInventory().setItem(2, PLAYER_PERKS);
        player.getInventory().setItem(4, new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(player.getName()).name("&aYour Stats").build());
        player.getInventory().setItem(8, EVENT_HOSTING);

        player.updateInventory();
    }

    public static void sendStats(Player player, User user) {
        final String[] stats = new String[]{
            "&7&m-----------------------------------------------------",
            "&a" + Bukkit.getPlayer(user.getUniqueId()).getName(),
            "&e Kills: &a" + user.getKills(),
            "&e Deaths: &c" + user.getDeaths(),
            "&e KD: &6" + (double) (user.getKills() / user.getDeaths()),
            "&e Killstreak: &6" + user.getCurrentKillstreak(),
            "&e Highest Killstreak: &6" + user.getHighestKillstreak(),
            "&e Credits: &a" + user.getCredits(),
            "&e Bounty: &c" + user.getBounty(),
            "&7&m-----------------------------------------------------"
        };

        for (String msg : stats) {
            player.sendMessage(ColorHelper.translate(msg));
        }
    }
}
