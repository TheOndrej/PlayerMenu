package me.TheOndra.PlayerMenu;

import me.TheOndra.PlayerMenu.Commands.PlayerMenu;
import me.TheOndra.PlayerMenu.Commands.PlayerMenuFileList;
import me.TheOndra.PlayerMenu.Listeners.ChatEvent;
import me.TheOndra.PlayerMenu.Listeners.ClickEvent;
import me.TheOndra.PlayerMenu.Listeners.JoinEvent;
import me.TheOndra.PlayerMenu.Listeners.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin{

    private static Main pl;
    private static PlayerMenu menu;
    private static ClickEvent clickEvent;
    HashMap<String, Inventory> inventory = new HashMap<>();
    List<Player> exp = new ArrayList<>();
    List<Player> health = new ArrayList<>();
    List<Player> food = new ArrayList<>();
    HashMap<String, Player> player = new HashMap<>();
    HashMap<String, String> offlinePlayer = new HashMap<>();
    List<Player> offlineXp = new ArrayList<>();
    List<Player> offlineHealth = new ArrayList<>();
    List<Player> offlineFood = new ArrayList<>();

    @Override
    public void onEnable(){
        getLogger().info("Plugin was enabled");
        pl = this;
        menu = new PlayerMenu();
        clickEvent = new ClickEvent();
        onCommands();
        onListeners();
    }

    @Override
    public void onDisable(){
        getLogger().info("Plugin was disabled");
        onPlayerConfig();
    }

    private void onCommands(){
        getCommand("playermenu").setExecutor(new PlayerMenu());
        getCommand("PlayerMenuFileList").setExecutor(new PlayerMenuFileList());
    }

    private void onListeners(){
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    private void onPlayerConfig(){

        for (Player p : Bukkit.getOnlinePlayers()){
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

    public static Main getPl() {
        return pl;
    }

    public static PlayerMenu getMenu() {
        return menu;
    }

    public HashMap<String, Inventory> getInventory() {
        return inventory;
    }

    public static ClickEvent getClickEvent() {
        return clickEvent;
    }

    public List<Player> getExp() {
        return exp;
    }

    public List<Player> getHealth() {
        return health;
    }

    public List<Player> getFood() {
        return food;
    }

    public HashMap<String, Player> getPlayer() {
        return player;
    }

    public HashMap<String, String> getOfflinePlayer() {
        return offlinePlayer;
    }

    public List<Player> getOfflineXp() {
        return offlineXp;
    }

    public List<Player> getOfflineHealth() {
        return offlineHealth;
    }

    public List<Player> getOfflineFood() {
        return offlineFood;
    }

    public ItemStack createItem(Material mat, int data, String name, List<String> lore, boolean enchanted)
    {
        ItemStack is;

        if(data != -1)
            is = new ItemStack(mat, 1, (byte) data);
        else
            is = new ItemStack(mat, 1);

        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        if(lore != null)
            im.setLore(lore);

        if(enchanted)
            im.addEnchant(Enchantment.LUCK, 1, true);

        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);

        return is;
    }
}
