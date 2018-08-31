package me.TheOndra.PlayerMenu.Listeners;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.Statistic;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class QuitEvent implements Listener{

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        File file = new File(Main.getPl().getDataFolder(), "Players");
        File f = new File(file, p.getName() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()){

            try {

                ConfigurationSection information = config.createSection("Information");
                String ip = p.getAddress().toString();
                information.set("IP", ip);
                if (p.isOp()){
                    information.set("OP", true);
                } else {
                    information.set("OP", false);
                }
                information.set("Level", p.getLevel());

                config.createSection("Location");
                config.set("Location", p.getLocation());

                config.createSection("Gamemode");
                String gamemode = p.getGameMode().name();
                config.set("Gamemode", gamemode);

                ConfigurationSection statistics = config.createSection("Statistics");
                statistics.set("Deaths", p.getStatistic(Statistic.DEATHS));
                statistics.set("Kills", p.getStatistic(Statistic.PLAYER_KILLS));
                statistics.set("Play", p.getStatistic(Statistic.PLAY_ONE_TICK));
                statistics.set("MobKill", p.getStatistic(Statistic.MOB_KILLS));

                config.createSection("Health");
                config.set("Health", p.getHealth());

                config.createSection("Food");
                config.set("Food", p.getFoodLevel());

                ConfigurationSection speed = config.createSection("Speed");
                speed.set("Walk", p.getWalkSpeed());
                speed.set("Fly", p.getFlySpeed());

                config.save(f);
            } catch (IOException exception){
                exception.printStackTrace();
            }
        } else {
            try {

                ConfigurationSection information = config.createSection("Information");
                String ip = p.getAddress().toString();
                information.set("IP", ip);
                if (p.isOp()){
                    information.set("OP", true);
                } else {
                    information.set("OP", false);
                }
                information.set("Level", p.getLevel());

                config.createSection("Location");
                config.set("Location", p.getLocation());

                String gamemode = p.getGameMode().name();
                config.set("Gamemode", gamemode);

                ConfigurationSection statistics = config.getConfigurationSection("Statistics");
                statistics.set("Deaths", p.getStatistic(Statistic.DEATHS));
                statistics.set("Kills", p.getStatistic(Statistic.PLAYER_KILLS));
                statistics.set("Play", p.getStatistic(Statistic.PLAY_ONE_TICK));
                statistics.set("MobKill", p.getStatistic(Statistic.MOB_KILLS));

                config.set("Health", p.getHealth());

                config.set("Food", p.getFoodLevel());

                ConfigurationSection speed = config.getConfigurationSection("Speed");
                speed.set("Walk", p.getWalkSpeed());
                speed.set("Fly", p.getFlySpeed());

                config.save(f);
            } catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
}
