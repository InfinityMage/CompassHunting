package club.infinitymage.compasshunting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntityEvent implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {

        if (event.getHand() == EquipmentSlot.HAND
                && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS
                && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE+"Player Tracker")
                && event.getPlayer().hasPermission("compasshunting.track")) {

            if (!event.getRightClicked().getType().equals(EntityType.PLAYER)) return;

            String p = event.getPlayer().getName();
            String target = event.getRightClicked().getName();

            TrackingManager.addTracking(p, target);
            event.getPlayer().sendMessage(Util.format("&2Now tracking &e"+event.getRightClicked().getName()+"&2."));

        }

    }

}
