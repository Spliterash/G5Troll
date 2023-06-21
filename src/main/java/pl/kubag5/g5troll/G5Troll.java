package pl.kubag5.g5troll;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kubag5.g5troll.trolls.*;

import java.util.ArrayList;

public final class G5Troll extends JavaPlugin {

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
            new CreeperSound()
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
