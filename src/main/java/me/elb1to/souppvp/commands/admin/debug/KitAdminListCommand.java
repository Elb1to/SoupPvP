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
 * Date: 5/6/2021 @ 9:55 PM
 */
public class KitAdminListCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "kitadmin.list", permission = "soup-pvp.admin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        player.sendMessage(ColorHelper.translate("&aTotal kits: " + SoupPvP.getInstance().getKitManager().getKits().size()));
        for (Kit kits : this.plugin.getKitManager().getKits()) {
            player.sendMessage(ColorHelper.translate("&a * " + kits.getName()));
        }
    }
}
