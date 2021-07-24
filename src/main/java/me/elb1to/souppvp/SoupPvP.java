package me.elb1to.souppvp;

import lombok.Getter;
import me.elb1to.souppvp.controller.ClassRegistrationController;
import me.elb1to.souppvp.controller.SpawnController;
import me.elb1to.souppvp.database.MongoSrv;
import me.elb1to.souppvp.loadout.ability.AbilityManager;
import me.elb1to.souppvp.loadout.kit.KitManager;
import me.elb1to.souppvp.layout.ServerScoreboard;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.user.UserManager;
import me.elb1to.souppvp.utils.ColorHelper;
import me.elb1to.souppvp.utils.command.CommandFramework;
import me.elb1to.souppvp.utils.scoreboard.BoardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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

	private MongoSrv mongoSrv;
	private KitManager kitManager;
	private UserManager userManager;
	private AbilityManager abilityManager;
	private SpawnController spawnController;

	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();

		Bukkit.getConsoleSender().sendMessage("------------------------------------------------");
		Bukkit.getConsoleSender().sendMessage(ColorHelper.translate("&bSoupPvP - Lunar.GG Replica &8- &fv" + getDescription().getVersion()));
		Bukkit.getConsoleSender().sendMessage(ColorHelper.translate("&7Made on &bFrozed Club Development &7by &bElb1to"));
		Bukkit.getConsoleSender().sendMessage("------------------------------------------------");

		this.loadManagers();
		crc.loadListeners("me.elb1to.souppvp.listeners");
		crc.loadCommands("me.elb1to.souppvp.commands");

		new BoardManager(new ServerScoreboard(), 20);
	}

    @Override
    public void onDisable() {
        for (User user : this.getUserManager().getAllUsers()) {
            this.userManager.saveUser(user);
        }

        for (Entity entity : this.getServer().getWorld("world").getEntities()) {
            if (entity.getType() == EntityType.DROPPED_ITEM) {
                entity.remove();
            }
        }

        this.mongoSrv.disconnect();
    }

	private void loadManagers() {
	    this.mongoSrv = new MongoSrv();
		this.kitManager = new KitManager();
		this.userManager = new UserManager();
		this.abilityManager = new AbilityManager();
		this.spawnController = new SpawnController();
	}
}
