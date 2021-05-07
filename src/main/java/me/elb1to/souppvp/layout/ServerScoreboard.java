package me.elb1to.souppvp.layout;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.PlayerProfile;
import me.elb1to.souppvp.utils.CC;
import me.elb1to.souppvp.utils.scoreboard.BoardAdapter;
import me.elb1to.souppvp.utils.scoreboard.BoardStyle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 2:24 PM
 */
public class ServerScoreboard implements BoardAdapter {

	private final SoupPvP plugin = SoupPvP.getInstance();

	@Override
	public String getTitle(Player player) {
		return CC.translate("&b&lSoupPvP");
	}

	@Override
	public List<String> getLines(Player player) {
		ArrayList<String> scoreboard = new ArrayList<>();
		PlayerProfile playerProfile = this.plugin.getProfileManager().getProfileByUuid(player.getUniqueId());

		scoreboard.add(CC.translate("&7&m----------------------"));
		scoreboard.add(CC.translate("Kills: &b" + playerProfile.getKills()));
		scoreboard.add(CC.translate("Killstreak: &b" + playerProfile.getCurrentKillstreak()));
		scoreboard.add(CC.translate("Deaths: &b" + playerProfile.getDeaths()));
		scoreboard.add(CC.translate("Credits: &b" + playerProfile.getCredits()));
		scoreboard.add(CC.translate(" "));
		scoreboard.add(CC.translate("&bfrozed.club"));
		scoreboard.add(CC.translate("&7&m----------------------"));

		return scoreboard;
	}

	@Override
	public BoardStyle getBoardStyle(Player player) {
		return BoardStyle.MODERN;
	}
}
