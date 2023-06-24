package pl.kubag5.g5troll;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kubag5.g5troll.trolls.*;

import java.util.ArrayList;
import java.util.Iterator;

public final class G5Troll extends JavaPlugin implements Listener {

    private static G5Troll trl;
    Troll[] trolls = {
            new Firework(),
            new Spin(),
            new Burn(),
            new Back(),
            new Cage(),
            new Anvil(),
            new Lightning(),
            new FakeTNT(),
            new Pumpkin(),
            new RandomizeInventory(),
            new ExplosionSound(),
            new CreeperSound(),
            new Hit(),
            new Hunger(),
            new InventoryDrop(),
            new FakeBlocks(),
            new Noob(),
            new FakeDiamondOre(),
            new Cobweb(),
            new Rotate(),
            new ScaryRandomTeleport(),
            new Funeral()
    };

    @Override
    public void onEnable() {
        trl = this;
        new TrollCmd(this);
        Freeze f = new Freeze();
        Bukkit.getServer().getPluginManager().registerEvents(f, this);
        addTroll(f);
        // https://bstats.org/signatures/bukkit/G5Troll.svg
        int pluginId = 18817;
        Metrics metrics = new Metrics(this, pluginId);
        getServer().getPluginManager().registerEvents(this, this);
        FileConfiguration config = this.getConfig();
        if (!config.isSet("changer")) {
            config.options().copyDefaults(true);
            this.saveDefaultConfig();
        }

    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        for (String st : getConfig().getConfigurationSection("changer").getKeys(false)) {
            Troll troll = getTrollByName(st);
            if (troll != null) {
                for (String str : getConfig().getConfigurationSection("changer." + st).getKeys(false)) {
                    if (str.equalsIgnoreCase("description")) {
                        troll.setDesc(getConfig().getString("changer." + st + ".description"));
                    }
                    if (str.equalsIgnoreCase("usage")) {
                        troll.setUsage(getConfig().getString("changer." + st + ".usage"));
                    }
                    if (str.startsWith("default_arg")) {
                        try {
                            int arg = Integer.parseInt(str.replaceFirst("default_arg", ""));
                            troll.setArg(arg-1, getConfig().getString("changer." + st + ".default_arg" + arg));
                        } catch (Exception ignored) {}
                    }
                }
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[G5Changer] " + st + " changed");
            } else {
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "[G5Changer] " + st + " don't exist.");
            }
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Troll[] getTrolls() {
        return trolls;
    }

    public Troll getTrollByName(String name) {
        for (Troll t : getTrolls()) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<String> getTrollArrayList() {
        ArrayList<String> list = new ArrayList<>();
        for (Troll t : getTrolls()) {
            list.add(t.getName());
        }
        return list;
    }

    public ArrayList<String> getTrollArrayListStartedWith(String str) {
        ArrayList<String> list = new ArrayList<>();
        for (Troll t : getTrolls()) {
            if (t.getName().toLowerCase().startsWith(str.toLowerCase())) {
                list.add(t.getName());
            }
        }
        return list;
    }

    public static G5Troll getInstance() {
        return trl;
    }

    public void addTroll(Troll t) {
        Troll[] newTrolls = new Troll[trolls.length + 1];
        for (int i = 0; i < trolls.length; i++) {
            newTrolls[i] = trolls[i];
        }
        newTrolls[newTrolls.length - 1] = t;
        trolls = newTrolls;
    }


}
