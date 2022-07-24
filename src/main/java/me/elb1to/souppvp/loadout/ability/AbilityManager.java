package me.elb1to.souppvp.loadout.ability;

import lombok.Getter;
import me.elb1to.souppvp.loadout.ability.impl.ShurikenAbility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/23/2021 @ 3:45 PM
 */
@Getter
public class AbilityManager {

    private final List<Ability> abilities = new ArrayList<>();

    public AbilityManager() {
        abilities.add(new ShurikenAbility());
    }

    public Ability getAbilityByName(String name) {
        return abilities.stream().filter(ability -> name.equals(ability.getName())).findFirst().orElse(null);
    }
}
