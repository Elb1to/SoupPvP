package me.elb1to.souppvp.utils.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by: ThatKawaiiSam
 * Project: Assemble
 */
@Getter @Setter
public class BoardEntry {

    private Board board;
    private String text;
    private String string;
    private Team team;

    public BoardEntry(Board board, String text) {
        this.board = board;
        this.text = text;
        this.string = board.getUniqueString();
        setUp();
    }

    public void setUp() {
        Scoreboard scoreboard = this.board.getScoreboard();
        if (scoreboard != null) {
            String name = this.string;
            if (name.length() > 16) name = name.substring(0, 16);

            Team team = scoreboard.getTeam(name);
            if (team == null) team = scoreboard.registerNewTeam(name);
            if (!team.getEntries().contains(this.string)) team.addEntry(this.string);
            if (!this.board.getEntries().contains(this)) this.board.getEntries().add(this);
            this.team = team;
        }
    }

    public void send(int position) {
        if (this.text.length() > 16) {
            String suffix;
            String prefix = this.text.substring(0, 16);

            if (prefix.charAt(15) == '§') {
                prefix = prefix.substring(0, 15);
                suffix = this.text.substring(15);
            } else if (prefix.charAt(14) == '§') {
                prefix = prefix.substring(0, 14);
                suffix = this.text.substring(14);
            } else if (ChatColor.getLastColors(prefix).equalsIgnoreCase(ChatColor.getLastColors(this.string))) {
                suffix = this.text.substring(16);
            } else {
                suffix = ChatColor.getLastColors(prefix) + this.text.substring(16);
            }

            if (suffix.length() > 16) suffix = suffix.substring(0, 16);
            this.team.setPrefix(prefix);
            this.team.setSuffix(suffix);
        } else {
            this.team.setPrefix(this.text);
            this.team.setSuffix("");
        }

        Score score = this.board.getObjective().getScore(this.string);
        score.setScore(position);
    }

    public void quit() {
        this.board.getStrings().remove(this.string);
        this.board.getScoreboard().resetScores(this.string);
    }
}
