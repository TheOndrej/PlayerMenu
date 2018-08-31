package me.TheOndra.PlayerMenu.Commands;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerMenuFileList implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("PlayerMenu.open")){
                if (args.length == 0) {
                    p.sendMessage("§cError: /pmfl player name");
                } else if (args.length == 1) {
                    File file = new File(Main.getPl().getDataFolder(), "Players");
                    File f = new File(file, args[0] + ".yml");

                    if (!f.exists()) {
                        p.sendMessage("§cThis file is not exists!");
                    } else {
                        p.sendMessage("§9File was deleted!");

                        f.delete();
                    }
                } else {
                    p.sendMessage("§cError: /pmfl player name");
                }
        }
        }
        return true;
    }
}
