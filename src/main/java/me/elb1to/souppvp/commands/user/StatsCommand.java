package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.PlayerUtil;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/18/2021 @ 10:40 PM
 */
public class StatsCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "stats", aliases = {"playerstats"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            PlayerUtil.sendStats(player, this.plugin.getUserManager().getByUuid(player.getUniqueId()));
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

        PlayerUtil.sendStats(player, this.plugin.getUserManager().getByUuid(target.getUniqueId()));
    }
}
