package me.TheOndra.PlayerMenu.Listeners;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class JoinEvent implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        File file = new File(Main.getPl().getDataFolder(), "Players");
        File f = new File(file, p.getName() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()){
            return;
        } else {
            if (config.getBoolean("Information.OP") == true){
                p.setOp(true);
            } else {
                p.setOp(false);
            }

            int i = config.getInt("Information.Level");
            p.setLevel(i);

                p.setGameMode(GameMode.valueOf(config.getString("Gamemode")));

                p.setHealth(config.getDouble("Health"));

                p.setFoodLevel(config.getInt("Food"));

                p.setWalkSpeed((float) config.getDouble("Speed.Walk"));
                p.setFlySpeed((float) config.getDouble("Speed.Fly"));

                Location loc = (Location) config.get("Location");
                World world = loc.getWorld();
                int x = loc.getBlockX();
                int y = loc.getBlockY();
                int z = loc.getBlockZ();
                Location location = new Location(world, x, y, z);
                p.teleport(location);

        }
    }
}
