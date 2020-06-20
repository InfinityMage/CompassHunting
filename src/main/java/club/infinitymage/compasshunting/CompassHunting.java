package club.infinitymage.compasshunting;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CompassHunting extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        PluginManager pm = Bukkit.getPluginManager();

        this.getCommand("givecompass").setExecutor(new GiveCompassCommand(this));

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEntityEvent(), this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Iterator it = TrackingManager.getTrackings().entrySet().iterator();
                while (it.hasNext()) {

                    Map.Entry pair = (Map.Entry) it.next();
                    Player p = getServer().getPlayer((String) pair.getKey());
                    Player target = getServer().getPlayer((String) pair.getValue());

                    if (target == null) {

                        ItemStack handItem = p.getInventory().getItemInMainHand();
                        ItemMeta compassMeta = handItem.getItemMeta();

                        List<String> compassLoreLines = new ArrayList<String>();
                        compassLoreLines.add(Util.format("&7Currently tracking:"));
                        compassLoreLines.add(Util.format("&c"+pair.getValue()));
                        compassLoreLines.add(Util.format(""));
                        compassLoreLines.add(Util.format("&7Target is offline."));

                        compassMeta.setLore(compassLoreLines);
                        handItem.setItemMeta(compassMeta);

                        p.setCompassTarget(new Location(p.getWorld(), 0, 64, 0));
                    }
                    else if (p != null) {
                        p.setCompassTarget(target.getLocation());

                        ItemStack handItem = p.getInventory().getItemInMainHand();
                        ItemMeta compassMeta = handItem.getItemMeta();

                        if (handItem.getType() == Material.COMPASS && handItem.getItemMeta().getDisplayName().equals(ChatColor.BLUE+"Player Tracker")) {

                            String pWorld = p.getWorld().getName();
                            String targetWorld = target.getWorld().getName();

                            String targetDistance = "";

                            if (pWorld.equals(targetWorld)) {
                                String trueDistance = String.valueOf((int) p.getLocation().distance(target.getLocation()));
                                targetDistance = trueDistance + "m";
                            }
                            else if (targetWorld.equals("world")) targetDistance = Util.format("&7Target is in the &aOverworld");
                            else if (targetWorld.equals("world_nether")) targetDistance = Util.format("&7Target is in &cThe Nether");
                            else if (targetWorld.equals("world_the_end")) targetDistance = Util.format("&7Target is in &5The End");
                            else targetDistance = Util.format("&7Target is in a different world.");

                            List<String> compassLoreLines = new ArrayList<String>();
                            compassLoreLines.add(Util.format("&7Currently tracking:"));
                            compassLoreLines.add(Util.format("&c"+target.getName()));
                            compassLoreLines.add(Util.format(""));
                            if (pWorld.equals(targetWorld)) compassLoreLines.add(Util.format("&7Distance to target:"));
                            compassLoreLines.add(Util.format("&2"+targetDistance));

                            compassMeta.setLore(compassLoreLines);
                            handItem.setItemMeta(compassMeta);

                        }
                    }
                }
            }
        }, 0L, 30L);

    }

    @Override
    public void onDisable() {

    }

}
