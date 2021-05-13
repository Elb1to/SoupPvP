package me.elb1to.souppvp.layout;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.scoreboard.BoardAdapter;
import me.elb1to.souppvp.utils.scoreboard.BoardStyle;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static me.elb1to.souppvp.utils.CC.translate;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 2:24 PM
 */
public class ServerScoreboard implements BoardAdapter {

    @Override
    public String getTitle(Player player) {
        return translate("&b&lSoupPvP");
    }

    @Override
    public List<String> getLines(Player player) {
        User user = SoupPvP.getInstance().getUserManager().getByUuid(player.getUniqueId());

        return Arrays.asList(
            translate("&7&m----------------------"),
            translate("Kills: &b" + user.getKills()),
            translate("Killstreak: &b" + user.getCurrentKillstreak()),
            translate("Deaths: &b" + user.getDeaths()),
            translate("Credits: &b" + user.getCredits()),
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
