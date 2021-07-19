package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import me.elb1to.souppvp.utils.cuboid.CustomLocation;
import org.bukkit.entity.Player;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/17/2021 @ 9:55 PM
 */
public class SetLocationCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "setlocation", permission = "soup-pvp.admin", usage = "&cUsage: /setlocation <spawn|min|max>")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(ColorHelper.translate(command.getCommand().getUsage()));
            return;
        }

        switch (args[0]) {
            case "spawn":
                this.plugin.getSpawnController().setSpawnLocation(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.LOCATION");
                player.sendMessage(ColorHelper.translate("&aSuccessfully saved spawn location."));
                break;
            case "max":
                this.plugin.getSpawnController().setSafezoneMax(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.SAFEZONE-MAX");
                player.sendMessage(ColorHelper.translate("&aSuccessfully saved safezone max location."));
                break;
            case "min":
                this.plugin.getSpawnController().setSafezoneMin(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.SAFEZONE-MIN");
                player.sendMessage(ColorHelper.translate("&aSuccessfully saved safezone min location."));
                break;
            default:
                player.sendMessage(ColorHelper.translate(command.getCommand().getUsage()));
                break;
        }
    }

    private void saveLocation(Player player, String location) {
        this.plugin.getConfig().set(location, CustomLocation.locationToString(CustomLocation.fromBukkitLocation(player.getLocation())));
        this.plugin.saveConfig();
    }
}
