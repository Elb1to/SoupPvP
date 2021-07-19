package me.elb1to.souppvp.user;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.elb1to.souppvp.SoupPvP;
import org.bson.Document;

import java.util.*;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/7/2021 @ 9:52 AM
 */
public class UserManager {

    @Getter private final Map<UUID, User> users = new HashMap<>();
    private final SoupPvP plugin = SoupPvP.getInstance();

    public User getOrCreate(UUID uuid) {
        return users.computeIfAbsent(uuid, User::new);
    }

    public User getByUuid(UUID uuid) {
        return users.getOrDefault(uuid, new User(uuid));
    }

    public Collection<User> getAllUsers() {
        return this.users.values();
    }

    public void loadUser(User user) {
        Document document = this.plugin.getMongoSrv().getUsers().find(Filters.eq("uniqueId", user.getUniqueId().toString())).first();
        if (document != null) {
            user.setCurrentKitName(document.getString("currentKitName"));
            user.setUnlockedKits((List<String>) document.get("unlockedKits"));

            user.setKills(document.getInteger("kills"));
            user.setDeaths(document.getInteger("deaths"));
            user.setBounty(document.getInteger("bounty"));
            user.setCredits(document.getInteger("credits"));
            user.setCurrentKillstreak(document.getInteger("currentKillstreak"));
            user.setHighestKillstreak(document.getInteger("highestKillstreak"));
        }

        user.setLoaded(true);
    }

    public void saveUser(User user) {
        Document document = new Document();
        document.put("uniqueId", user.getUniqueId().toString());

        document.put("currentKitName", user.getCurrentKitName());
        document.put("unlockedKits", user.getUnlockedKits());

        document.put("kills", user.getKills());
        document.put("deaths", user.getDeaths());
        document.put("bounty", user.getBounty());
        document.put("credits", user.getCredits());
        document.put("currentKillstreak", user.getCurrentKillstreak());
        document.put("highestKillstreak", user.getHighestKillstreak());

        this.plugin.getMongoSrv().getUsers().replaceOne(Filters.eq("uniqueId", user.getUniqueId().toString()), document, new UpdateOptions().upsert(true));
    }

    public void deleteUser(UUID uniqueId) {
        this.saveUser(this.getByUuid(uniqueId));
        this.getUsers().remove(uniqueId);
    }
}
