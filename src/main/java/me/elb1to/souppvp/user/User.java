package me.elb1to.souppvp.user;

import lombok.Getter;
import lombok.Setter;
import me.elb1to.souppvp.SoupPvP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 9:37 AM
 */
@Getter @Setter
public class User {

    private final UserManager userManager = SoupPvP.getInstance().getUserManager();

    private final UUID uniqueId;

    private boolean loaded;

    private String currentKitName = "Default";
    private List<String> unlockedKits = new ArrayList<>();

    private int kills;
    private int deaths;
    private int bounty;
    private int credits;
    private int currentKillstreak;
    private int highestKillstreak;

    public User(UUID uuid) {
        this.uniqueId = uuid;
        this.loaded = false;
        this.unlockedKits.add("Default");

        getUserManager().loadUser(this);
    }
}
