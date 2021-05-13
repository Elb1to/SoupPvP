package me.elb1to.souppvp.kit;

import lombok.Getter;
import me.elb1to.souppvp.kit.impl.DefaultKit;
import me.elb1to.souppvp.kit.impl.ProKit;

import java.util.ArrayList;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 4:31 PM
 */
@Getter
public class KitManager {

	private final ArrayList<Kit> kits = new ArrayList<>();

	public Kit getKitByName(String name) {
		for (Kit kit : kits) {
			if (name.equals(kit.getName())) {
				return kit;
			}
		}

		return null;
	}

	public void loadKits() {
		kits.add(new DefaultKit());
		kits.add(new ProKit());
	}
}
