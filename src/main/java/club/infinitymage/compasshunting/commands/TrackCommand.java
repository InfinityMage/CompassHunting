package club.infinitymage.compasshunting.commands;

import club.infinitymage.compasshunting.CompassHunting;
import club.infinitymage.compasshunting.managers.TrackingManager;
import club.infinitymage.compasshunting.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrackCommand implements CommandExecutor {

    private CompassHunting plugin;

    public TrackCommand(CompassHunting plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if(!cmdName.equals("track")) return false;

        if (args.length == 0) {
            sender.sendMessage(Util.format("&cIncorrect usage! The correct usage is /track {target} [player]"));
        }

        // one argument - track target with the hunter as the command executor
        else if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Util.format("&cYou must specify two arguments as the console: /track {target} {player}"));
            } else {
                Player p = (Player) sender;
                if (p.hasPermission("compasshunting.track.command") || p.hasPermission("compasshunting.track.others")) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        TrackingManager.addTracking(p.getName(), target.getName());
                        p.sendMessage(Util.format("&2Now tracking &e"+target.getName()+"&2."));
                    } else {
                        p.sendMessage(Util.format("&cThat player could not be found."));
                    }
                } else {
                    p.sendMessage(Util.format("&cYou do not have permission for that command."));
                }
            }
        }

        // two or more arguments - track target with the hunter as the second argument
        else {
            if (sender.hasPermission("compasshunting.track.others")) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                Player hunter = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(Util.format("&cCould not find target player."));
                }
                else if (hunter == null) {
                    sender.sendMessage(Util.format("&cCould not find hunting player."));
                }
                else {
                    TrackingManager.addTracking(hunter.getName(), target.getName());
                    sender.sendMessage(Util.format("&e"+hunter.getName()+" is now tracking &e"+target.getName()+"&2."));
                    hunter.sendMessage(Util.format("&2Now tracking &e"+target.getName()+"&2."));
                }
            } else {
                sender.sendMessage(Util.format("&cYou do not have permission for that command."));
            }
        }

        return true;

    }

}