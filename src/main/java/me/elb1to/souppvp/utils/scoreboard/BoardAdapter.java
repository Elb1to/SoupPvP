package me.elb1to.souppvp.utils.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by: ThatKawaiiSam
 * Project: Assemble
 */
public interface BoardAdapter {

	String getTitle(Player player);

	List<String> getLines(Player player);

	BoardStyle getBoardStyle(Player player);
}

