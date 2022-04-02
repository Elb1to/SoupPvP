package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.ui.kit.KitSelectionMenu;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/13/2021 @ 11:15 AM
 */
public class KitCommand extends BaseCommand {

    @Override @Command(name = "kit", aliases = {"kits"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (SoupPvP.getInstance().getCombatManager().isCombat(player)) {
            player.sendMessage(translate("&cYou can't use commands whilst in combat."));
            return;
        }
        new KitSelectionMenu().openMenu(command.getPlayer());
    }
}
