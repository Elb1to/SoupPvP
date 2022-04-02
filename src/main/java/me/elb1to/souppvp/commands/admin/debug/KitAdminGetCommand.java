package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:56 PM
 */
public class KitAdminGetCommand extends BaseCommand {

    @Override @Command(name = "kitadmin.get", permission = "soup-pvp.admin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(translate("&cUsage: /kitadmin get <kitName>"));
            return;
        }

        if (SoupPvP.getInstance().getKitManager().getKitByName(args[0]) != null) {
            SoupPvP.getInstance().getKitManager().getKitByName(args[0]).equipKit(player);
        } else {
            player.sendMessage(translate("&cThis kit doesnt exist"));
        }
    }
}
