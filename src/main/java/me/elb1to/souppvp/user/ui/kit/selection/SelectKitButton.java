package me.elb1to.souppvp.user.ui.kit.selection;

import lombok.AllArgsConstructor;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.kit.Kit;
import me.elb1to.souppvp.user.User;
import me.elb1to.souppvp.utils.CC;
import me.elb1to.souppvp.utils.ItemBuilder;
import me.elb1to.souppvp.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 5/13/2021 @ 2:07 PM
 */
@AllArgsConstructor
public class SelectKitButton extends Button {

    private final Kit kit;
    private final User user;

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.addAll(Arrays.asList(kit.getDesc()));
        lore.add(CC.MENU_BAR);
        if (!user.getUnlockedKits().contains(kit.getName())) {
            lore.add("&ePrice: " + kit.getPrice() + "&e.");
            lore.add("&aClick here to purchase.");
        }

        return new ItemBuilder(kit.getIcon()).name(CC.translate("&a" + kit.getName())).lore(CC.translate(lore)).hideFlags().build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (!user.getUnlockedKits().contains(kit.getName()) && user.getCredits() < kit.getPrice()) {
            player.sendMessage(CC.translate("&cNot enough credits!"));
            return;
        }
        if (!user.getUnlockedKits().contains(kit.getName()) && user.getCredits() >= kit.getPrice()) {
            user.getUnlockedKits().add(kit.getName());
            user.setCredits(user.getCredits() - kit.getPrice());
            player.sendMessage(CC.translate("&eYou have purchased the &d" + kit.getName() + "&e kit."));
            return;
        }

        playSuccess(player);
        player.closeInventory();
        user.setCurrentKitName(kit.getName());
        SoupPvP.getInstance().getKitManager().getKitByName(kit.getName()).equipKit(player);
    }
}
