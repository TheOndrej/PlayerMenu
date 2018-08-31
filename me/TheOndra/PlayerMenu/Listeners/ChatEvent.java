package me.TheOndra.PlayerMenu.Listeners;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;

public class ChatEvent implements Listener{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        File file = new File(Main.getPl().getDataFolder(), "Players");
        File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

        if (Main.getPl().getExp().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getExp().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                float i = 0;
                float floa = i + Integer.parseInt(e.getMessage());

                Main.getPl().getPlayer().get(p.getName()).setExp(floa);
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Exp level was changed.");
                Main.getPl().getExp().remove(p);
            }
        } else if (Main.getPl().getHealth().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getHealth().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                double i = Double.parseDouble(e.getMessage());

                Main.getPl().getPlayer().get(p.getName()).setHealth(i);
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Health was changed.");
                Main.getPl().getHealth().remove(p);
            }
        } else if (Main.getPl().getFood().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getFood().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                int i = Integer.parseInt(e.getMessage());

                Main.getPl().getPlayer().get(p.getName()).setFoodLevel(i);
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Food level was changed.");
                Main.getPl().getFood().remove(p);
            }
        } else if (Main.getPl().getOfflineXp().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getOfflineXp().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                float i = 0;
                float floa = i + Integer.parseInt(e.getMessage());

                try {
                    config.set("Information.Level", floa);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Exp level was changed.");
                Main.getPl().getOfflineXp().remove(p);
            }
        } else if (Main.getPl().getOfflineHealth().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getOfflineHealth().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                double i = Double.parseDouble(e.getMessage());

                try {
                    config.set("Health", i);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Health was changed.");
                Main.getPl().getOfflineHealth().remove(p);
            }
        } else if (Main.getPl().getOfflineFood().contains(p)){
            e.setCancelled(true);
            if (e.getMessage().equals("cancel")){
                Main.getPl().getOfflineFood().remove(p);
                p.sendMessage("§7Cancel!");
            } else {
                int i = Integer.parseInt(e.getMessage());

                try {
                    config.set("Food", i);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Food level was changed.");
                Main.getPl().getOfflineFood().remove(p);
            }
        }
    }
}
