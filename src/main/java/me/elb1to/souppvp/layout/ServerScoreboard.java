package me.elb1to.souppvp.layout;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.PlayerProfile;
import me.elb1to.souppvp.utils.CC;
import me.elb1to.souppvp.utils.scoreboard.BoardAdapter;
import me.elb1to.souppvp.utils.scoreboard.BoardStyle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.elb1to.souppvp.utils.CC.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 2:24 PM
 */
public class ServerScoreboard implements BoardAdapter {

	private final SoupPvP plugin = SoupPvP.getInstance();

	@Override
	public String getTitle(Player player) {
		return translate("&b&lSoupPvP");
	}

	@Override
	public List<String> getLines(Player player) {
		PlayerProfile playerProfile = this.plugin.getProfileManager().getProfileByUuid(player.getUniqueId());

        return Arrays.asList(
            translate("&7&m----------------------"),
            translate("Kills: &b" + playerProfile.getKills()),
            translate("Killstreak: &b" + playerProfile.getCurrentKillstreak()),
            translate("Deaths: &b" + playerProfile.getDeaths()),
            translate("Credits: &b" + playerProfile.getCredits()),
            translate(" "),
            translate("&bfrozed.club"),
            translate("&7&m----------------------")
        );
	}

	@Override
	public BoardStyle getBoardStyle(Player player) {
		return BoardStyle.MODERN;
	}
}
