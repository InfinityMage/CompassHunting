package club.infinitymage.compasshunting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCompassCommand implements CommandExecutor {

    private CompassHunting plugin;

    public GiveCompassCommand(CompassHunting plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if(!cmdName.equals("givecompass")) return false;

        ItemStack compass = new ItemStack(Material.COMPASS);

        ItemMeta compassMeta = compass.getItemMeta();

        compassMeta.setDisplayName(Util.format("&9Player Tracker"));
        compass.setItemMeta(compassMeta);

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(Util.format("&cYou must specify a player to give a compass to!"));
            } else {
                if (Bukkit.getServer().getPlayer(args[0]) != null) {
                    Bukkit.getServer().getPlayer(args[0]).getInventory().addItem(compass);
                    sender.sendMessage(Util.format("&2Given &e"+Bukkit.getServer().getPlayer(args[0]).getName()+" &2the hunting compass."));
                } else {
                    sender.sendMessage(Util.format("&cThat player does not exist."));
                }
            }
        } else {

            Player p = (Player) sender;

            if (p.hasPermission("compasshunting.give")) {

                if (args.length == 0) {
                    p.getInventory().addItem(compass);
                    sender.sendMessage(Util.format("&2Given &e"+p.getName()+" &2the hunting compass."));
                } else {
                    if (Bukkit.getServer().getPlayer(args[0]) != null) {
                        Bukkit.getServer().getPlayer(args[0]).getInventory().addItem(compass);
                        sender.sendMessage(Util.format("&2Given &e"+Bukkit.getServer().getPlayer(args[0]).getName()+" &2the hunting compass."));
                    } else {
                        sender.sendMessage(Util.format("&cThat player does not exist."));
                    }
                }

            } else {
                sender.sendMessage(Util.format("&cYou do not have permission for that command."));
            }

        }

        return true;

    }

}

