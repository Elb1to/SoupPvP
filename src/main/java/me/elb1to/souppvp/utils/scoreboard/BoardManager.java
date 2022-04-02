package me.elb1to.souppvp.utils.scoreboard;

import lombok.Getter;
import me.elb1to.souppvp.SoupPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

import static me.elb1to.souppvp.utils.ColorHelper.translate;

/**
 * Created by: ThatKawaiiSam
 * Project: Assemble
 */
@Getter
public class BoardManager {

	private final BoardAdapter adapter;
	private final Map<UUID, Board> boardMap;

	public BoardManager(BoardAdapter adapter, int updateTick) {
		this.adapter = adapter;
		this.boardMap = new HashMap<>();

		Bukkit.getPluginManager().registerEvents(new BoardListener(this), SoupPvP.getInstance());
		Bukkit.getScheduler().runTaskTimer(SoupPvP.getInstance(), this::sendScoreboard, 0, updateTick);
	}

	public void sendScoreboard() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			Board board = this.boardMap.get(player.getUniqueId());
			if (board != null) {
				Scoreboard scoreboard = board.getScoreboard();
				Objective objective = board.getObjective();
				String title = translate(this.adapter.getTitle(player));
				if (!objective.getDisplayName().equals(title)) {
					objective.setDisplayName(title);
				}

				List<String> lines = this.adapter.getLines(player);
				if (lines == null || lines.isEmpty()) {
					board.getEntries().forEach(BoardEntry::quit);
					board.getEntries().clear();
				} else {
					if (!this.adapter.getBoardStyle(player).isDescending()) {
						Collections.reverse(lines);
					}
					if (board.getEntries().size() > lines.size()) {
						for (int j = lines.size(); j < board.getEntries().size(); j++) {
							BoardEntry entry = board.getEntryAtPosition(j);
							if (entry != null) {
								entry.quit();
							}
						}
					}

					int cache = this.adapter.getBoardStyle(player).getStart();
					for (int i = 0; i < lines.size(); i++) {
						BoardEntry entry = board.getEntryAtPosition(i);
						String line = translate(lines.get(i));
						if (entry == null) {
							entry = new BoardEntry(board, line);
						}

						entry.setText(line);
						entry.setUp();
						entry.send(this.adapter.getBoardStyle(player).isDescending() ? cache-- : cache++);
					}
				}

				if (player.getScoreboard() == scoreboard) {
					continue;
				}

				player.setScoreboard(scoreboard);
			}
		}
	}
}
