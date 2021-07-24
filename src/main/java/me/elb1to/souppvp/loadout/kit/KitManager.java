package me.elb1to.souppvp.loadout.kit;

import lombok.Getter;
import me.elb1to.souppvp.loadout.kit.impl.CopyCatKit;
import me.elb1to.souppvp.loadout.kit.impl.DefaultKit;
import me.elb1to.souppvp.loadout.kit.impl.NinjaKit;
import me.elb1to.souppvp.loadout.kit.impl.ProKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/6/2021 @ 4:31 PM
 */
@Getter
public class KitManager {

    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        kits.add(new DefaultKit());
        kits.add(new ProKit());
        kits.add(new CopyCatKit());
        kits.add(new NinjaKit());
    }

    public Kit getKitByName(String name) {
        for (Kit kit : kits) {
            if (name.equals(kit.getName())) {
                return kit;
            }
        }

        return null;
    }
}
