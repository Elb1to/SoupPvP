package me.elb1to.souppvp.layout;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.scoreboard.BoardAdapter;
import me.elb1to.souppvp.utils.scoreboard.BoardStyle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

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
        User user = SoupPvP.getInstance().getUserManager().getByUuid(player.getUniqueId());
        List<String> strings = new ArrayList<>();

        strings.add(translate("&7&m----------------------"));
        strings.add(translate("Kills: &b" + user.getKills()));
        strings.add(translate("Killstreak: &b" + user.getCurrentKillstreak()));
        strings.add(translate("Deaths: &b" + user.getDeaths()));
        strings.add(translate("Credits: &b" + user.getCredits()));
        if (plugin.getCombatManager().isCombat(player)) {
        strings.add(translate("&cCombat Tag&7: &f" + plugin.getCombatManager().getCombatTime(player) + "s"));
        }
        strings.add(translate(" "));
        strings.add(translate("&bfrozed.club"));
        strings.add(translate("&7&m----------------------"));

        return strings;
    }


    @Override
    public BoardStyle getBoardStyle(Player player) {
        return BoardStyle.MODERN;
    }
}
