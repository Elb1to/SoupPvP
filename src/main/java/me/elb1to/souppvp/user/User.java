package me.elb1to.souppvp.user;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import lombok.Setter;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.database.MongoSrv;
import org.bson.Document;

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

	private int credits;
	private int kills;
	private int deaths;
	private int currentKillstreak;
	private int highestKillstreak;

	public User(UUID uuid) {
		this.uniqueId = uuid;
		this.loaded = false;

		load();
		this.unlockedKits.add("Default");
	}

	public void load() {
		Document document = MongoSrv.getInstance().getUsers().find(Filters.eq("uniqueId", uniqueId.toString())).first();
		if (document != null) {
            this.currentKitName = document.getString("currentKitName");
            this.unlockedKits = (List<String>) document.get("unlockedKits");

			this.kills = document.getInteger("kills");
			this.deaths = document.getInteger("deaths");
			this.credits = document.getInteger("credits");
			this.currentKillstreak = document.getInteger("currentKillstreak");
			this.highestKillstreak = document.getInteger("highestKillstreak");
		}

		this.loaded = true;
	}

	public void save() {
		Document document = new Document();
		document.put("uniqueId", this.uniqueId.toString());

        document.put("currentKitName", this.currentKitName);
        document.put("unlockedKits", this.unlockedKits);

		document.put("kills", this.kills);
		document.put("deaths", this.deaths);
		document.put("credits", this.credits);
		document.put("currentKillstreak", this.currentKillstreak);
		document.put("highestKillstreak", this.highestKillstreak);

		MongoSrv.getInstance().getUsers().replaceOne(Filters.eq("uniqueId", uniqueId.toString()), document, new UpdateOptions().upsert(true));
	}

	public void delete() {
		this.save();

		userManager.getUsers().remove(this.uniqueId);
	}
}
