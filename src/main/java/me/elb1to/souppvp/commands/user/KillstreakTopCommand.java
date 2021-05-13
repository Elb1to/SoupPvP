package me.elb1to.souppvp.commands.user;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.command.BaseCommand;
import me.elb1to.souppvp.utils.command.Command;
import me.elb1to.souppvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/13/2021 @ 11:22 AM
 */
public class KillstreakTopCommand extends BaseCommand {

    private final SoupPvP plugin = SoupPvP.getInstance();

    @Override @Command(name = "killstreakstop", aliases = {"kstop", "kst"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        // Show top 10 players with highest killstreaks.
    }
}
