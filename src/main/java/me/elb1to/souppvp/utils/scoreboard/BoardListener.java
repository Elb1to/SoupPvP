package me.elb1to.souppvp.utils.scoreboard;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by: ThatKawaiiSam
 * Project: Assemble
 */
@AllArgsConstructor
public class BoardListener implements Listener {

	private BoardManager board;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.board.getBoardMap().put(event.getPlayer().getUniqueId(), new Board(event.getPlayer(), this.board));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.board.getBoardMap().remove(event.getPlayer().getUniqueId());
	}
}

