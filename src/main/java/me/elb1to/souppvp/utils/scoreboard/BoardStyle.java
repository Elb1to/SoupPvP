package me.elb1to.souppvp.utils.scoreboard;

import lombok.Getter;

/**
 * Created by: ThatKawaiiSam
 * Project: Assemble
 */
@Getter
public enum BoardStyle {

	MODERN(false, 1);

	private final int start;
	private final boolean descending;

	BoardStyle(boolean descending, int start) {
		this.descending = descending;
		this.start = start;
	}
}
