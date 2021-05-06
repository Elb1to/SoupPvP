package me.elb1to.souppvp;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SoupPvP extends JavaPlugin {

	@Getter private static SoupPvP instance;

	@Override
	public void onEnable() {
		instance = this;
	}

	@Override
	public void onDisable() {

	}
}
