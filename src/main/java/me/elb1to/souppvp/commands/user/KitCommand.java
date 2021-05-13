package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.user.ui.kit.KitSelectionMenu;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/13/2021 @ 11:15 AM
 */
public class KitCommand extends BaseCommand {

    @Override @Command(name = "kit", aliases = {"kits"})
    public void onCommand(CommandArgs command) {
        new KitSelectionMenu().openMenu(command.getPlayer());
    }
}
