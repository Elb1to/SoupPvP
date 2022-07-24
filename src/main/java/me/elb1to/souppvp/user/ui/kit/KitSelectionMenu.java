package me.elb1to.souppvp.user.ui.kit;

import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.loadout.kit.Kit;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.user.ui.kit.selection.SelectKitButton;
import me.elb1to.souppvp.utils.menu.Button;
import me.elb1to.souppvp.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/13/2021 @ 12:38 PM
 */
public class KitSelectionMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Kit Selection";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        SoupPvP.getInstance().getKitManager().getKits().forEach(kit -> buttons.put(buttons.size(), new SelectKitButton(kit, SoupPvP.getInstance().getUserManager().getByUuid(player.getUniqueId()))));

        return buttons;
    }
}
