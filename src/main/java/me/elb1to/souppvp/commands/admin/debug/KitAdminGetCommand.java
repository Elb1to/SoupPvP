package me.elb1to.souppvp.commands.admin.debug;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.loadout.kit.Kit;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 9:56 PM
 */
public class KitAdminGetCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override
    @Command(name = "kitadmin.get", permission = "soup-pvp.admin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length != 1) {
            player.sendMessage(ColorHelper.translate("&cUsage: /kitadmin get <kitName>"));
            return;
        }

        Kit kitByName = plugin.getKitManager().getKitByName(args[0]);

        if (kitByName != null) {
            kitByName.equipKit(player);
        } else {
            player.sendMessage(ColorHelper.translate("&cThis kit doesnt exist"));
        }


    }
}
