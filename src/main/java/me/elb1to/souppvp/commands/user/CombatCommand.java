package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by Infames
 * Date: 24/07/2021 @ 19:45
 */
public class CombatCommand extends BaseCommand {

    @Override @Command(name = "combat", aliases = {"ct", "tag", "spawntag"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (SoupPvP.getInstance().getCombatManager().isCombat(player)) {
            player.sendMessage(translate("&cYou are in combat for " + SoupPvP.getInstance().getCombatManager().getCombatTime(player) + "s"));
        } else {
            player.sendMessage(translate(("&aYou are not in combat.")));
        }
    }
}
