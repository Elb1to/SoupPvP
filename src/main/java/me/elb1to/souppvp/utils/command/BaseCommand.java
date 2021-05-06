package me.elb1to.souppvp.utils.command;

import me.elb1to.souppvp.SoupPvP;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 11:25 AM
 */
public abstract class BaseCommand {

	public BaseCommand() {
		SoupPvP.getInstance().getCommandFramework().registerCommands(this, null);
	}

	public abstract void onCommand(CommandArgs command);
}
