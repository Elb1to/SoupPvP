package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import me.elb1to.souppvp.utils.cuboid.CustomLocation;
import org.bukkit.entity.Player;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/17/2021 @ 9:55 PM
 */
public class SetLocationCommand extends BaseCommand {

    @Override @Command(name = "setlocation", permission = "soup-pvp.admin", usage = "&cUsage: /setlocation <spawn|min|max>")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(translate(command.getCommand().getUsage()));
            return;
        }

        switch (args[0]) {
            case "spawn":
                SoupPvP.getInstance().getSpawnController().setSpawnLocation(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.LOCATION");
                player.sendMessage(translate("&aSuccessfully saved spawn location."));
                break;
            case "max":
                SoupPvP.getInstance().getSpawnController().setSafezoneMax(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.SAFEZONE-MAX");
                player.sendMessage(translate("&aSuccessfully saved safezone max location."));
                break;
            case "min":
                SoupPvP.getInstance().getSpawnController().setSafezoneMin(CustomLocation.fromBukkitLocation(player.getLocation()));
                saveLocation(player, "SERVER.SPAWN.SAFEZONE-MIN");
                player.sendMessage(translate("&aSuccessfully saved safezone min location."));
                break;
            default:
                player.sendMessage(translate(command.getCommand().getUsage()));
                break;
        }
    }

    private void saveLocation(Player player, String location) {
        SoupPvP.getInstance().getConfig().set(location, CustomLocation.locationToString(CustomLocation.fromBukkitLocation(player.getLocation())));
        SoupPvP.getInstance().saveConfig();
    }
}
