package me.elb1to.souppvp.commands.user;

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
 * Date: 5/13/2021 @ 11:23 AM
 */
public class PayCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "pay", aliases = "p2p")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (plugin.getCombatManager().isCombat(player)) {
            player.sendMessage(translate("&cYou can't use commands whilst in combat."));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(translate("&cUsage: /pay <player> <amount>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(translate("&cThere are no players named '" + args[0] + "' online."));
            return;
        }
        if (target == player) {
            player.sendMessage(translate("&cYou cannot send money to yourself!"));
            return;
        }

        User targetUser = this.plugin.getUserManager().getByUuid(target.getUniqueId());
        if (targetUser == null) {
            player.sendMessage(translate("&c" + target.getName() + " doesn't have data stored."));
            return;
        }

        int creditsSent = Integer.parseInt(args[1]);
        if (creditsSent <= 0) {
            player.sendMessage(translate("&cYou can't send that amount of credits."));
            return;
        }

        User user = this.plugin.getUserManager().getByUuid(player.getUniqueId());
        if (user.getCredits() < creditsSent) {
            player.sendMessage(translate("&cYou don't have enough credits to send."));
            return;
        }

        user.setCredits(user.getCredits() - creditsSent);
        targetUser.setCredits(targetUser.getCredits() + creditsSent);

        String credits = (creditsSent > 1 ? "credits" : "credit");
        player.sendMessage(translate("&eYou sent &d" + args[1] + "&e " + credits + " to &d" + target.getName() + "&e."));
        target.sendMessage(translate("&d" + player.getName() + "&e sent you &d" + args[1] + "&e " + credits + "."));
    }
}
