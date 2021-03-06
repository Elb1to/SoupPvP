package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 5:33 PM
 */
public class SetCreditsCommand extends BaseCommand {

    @Override @Command(name = "setcredits", aliases = "setbalance", permission = "soup-pvp.admin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 2) {
            player.sendMessage(ColorHelper.translate("&cUsage: /setcredits <player> <credits>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ColorHelper.translate("&cThere are no players named '" + args[0] + "' online."));
            return;
        }

        User targetUser = SoupPvP.getInstance().getUserManager().getByUuid(target.getUniqueId());
        if (targetUser == null) {
            player.sendMessage(ColorHelper.translate("&c" + target.getName() + " doesn't have data stored."));
            return;
        }

        targetUser.setCredits(Integer.parseInt(args[1]));
        player.sendMessage(ColorHelper.translate("&aSuccessfully modified " + target.getName() + "'s credits amount to " + args[1] + "."));
    }
}
