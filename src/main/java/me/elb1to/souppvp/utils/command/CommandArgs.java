package me.elb1to.souppvp.utils.command;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command Framework - BukkitCommand
 * An implementation of Bukkit's Command class allowing for registering of commands without plugin.yml
 *
 * @author minnymin3
 */
@Getter
public class CommandArgs {

	private CommandSender sender;
	private org.bukkit.command.Command command;
	private String label;
	private String[] args;

	protected CommandArgs(CommandSender sender, org.bukkit.command.Command command, String label, String[] args, int subCommand) {
		String[] modArgs = new String[args.length - subCommand];
		for (int i = 0; i < args.length - subCommand; i++) {
			modArgs[i] = args[i + subCommand];
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(label);
		for (int x = 0; x < subCommand; x++) {
			buffer.append("." + args[x]);
		}
		String cmdLabel = buffer.toString();
		this.sender = sender;
		this.command = command;
		this.label = cmdLabel;
		this.args = modArgs;
	}

	public String getArgs(int index) {
		return args[index];
	}

	public int length() {
		return args.length;
	}

	public boolean isPlayer() {
		return sender instanceof Player;
	}

	public Player getPlayer() {
		if (sender instanceof Player) {
			return (Player) sender;
		} else {
			return null;
		}
	}
}
