package me.elb1to.souppvp;

import lombok.Getter;
import me.elb1to.souppvp.controller.ClassRegistrationController;
import me.elb1to.souppvp.database.MongoSrv;
import me.elb1to.souppvp.kit.KitManager;
import me.elb1to.souppvp.layout.ServerScoreboard;
import me.elb1to.souppvp.user.UserManager;
import me.elb1to.souppvp.utils.CC;
import me.elb1to.souppvp.utils.command.CommandFramework;
import me.elb1to.souppvp.utils.menu.MenuUpdateTask;
import me.elb1to.souppvp.utils.scoreboard.BoardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 11:25 AM
 */
@Getter
public final class SoupPvP extends JavaPlugin {

	@Getter private static SoupPvP instance;
	private final CommandFramework commandFramework = new CommandFramework(this);
	private final ClassRegistrationController crc = new ClassRegistrationController();

	private KitManager kitManager;
	private UserManager userManager;

	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();

		Bukkit.getConsoleSender().sendMessage("------------------------------------------------");
		Bukkit.getConsoleSender().sendMessage(CC.translate("&bSoupPvP - Lunar.GG Replica &8- &fv" + getDescription().getVersion()));
		Bukkit.getConsoleSender().sendMessage(CC.translate("&7Made on &bFrozed Club Development &7by &bElb1to"));
		Bukkit.getConsoleSender().sendMessage("------------------------------------------------");

		new MongoSrv();

		this.loadManagers();
		crc.loadListeners("me.elb1to.souppvp.listeners");
		crc.loadCommands("me.elb1to.souppvp.commands");

		new BoardManager(new ServerScoreboard(), 20);
	}

	@Override
	public void onDisable() {

	}

	private void loadManagers() {
		this.kitManager = new KitManager();
		this.kitManager.loadKits();

		this.userManager = new UserManager();
	}
}
