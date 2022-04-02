package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 8/30/2021 @ 11:26 AM
 */
public class SetStatCommand extends BaseCommand {

    @Override @Command(name = "setstat", permission = "soup-pvp.admin", aliases = "setstats", usage = "Usage: /setstat <player> <kills/deaths> <amount>")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 3) {
            player.sendMessage(translate("&c" + command.getCommand().getUsage()));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(translate("&cThere are no players named '" + args[0] + "' online."));
            return;
        }

        User targetUser = SoupPvP.getInstance().getUserManager().getByUuid(target.getUniqueId());
        if (targetUser == null) {
            player.sendMessage(translate("&c" + target.getName() + " doesn't have data stored."));
            return;
        }

        int amount = Integer.parseInt(args[2]);
        switch (args[1].toLowerCase()) {
            case "kills":
                targetUser.setKills(amount);
                break;
            case "deaths":
                targetUser.setDeaths(amount);
                break;
            default:
                player.sendMessage(translate("&c" + command.getCommand().getUsage()));
                break;
        }

        player.sendMessage(translate("&aSuccessfully modified " + target.getName() + "'s " + args[1] + " amount to " + amount + "."));
    }
}
