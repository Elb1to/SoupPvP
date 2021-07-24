package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Infames
 * Date: 24/07/2021 @ 19:45
 */
public class CombatCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "combat", aliases = {"ct", "tag", "spawntag"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (plugin.getCombatManager().isCombat(player)) {
            player.sendMessage(ColorHelper.translate("&cYou are in combat for " + plugin.getCombatManager().getCombatTime(player) + "s"));
        } else {
            player.sendMessage(ColorHelper.translate(("&aYou are not in combat.")));
        }
    }
}
