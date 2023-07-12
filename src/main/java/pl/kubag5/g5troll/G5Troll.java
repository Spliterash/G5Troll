package pl.kubag5.g5troll;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kubag5.g5troll.trolls.*;
import pl.kubag5.g5troll.trolls.Void;

import java.util.ArrayList;

public final class G5Troll extends JavaPlugin implements Listener {

    private static G5Troll trl;
    public G5API api;
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
            new Funeral(),
            new AntiMiner(),
            new SheepTroll(),
            new EndermanJumpscare(),
            new MobHat(),
            new MobRide(),
            new DirtInventory(),
            new AirShots(),
            new BlockRain(),
            new Swap(),
            new Void(),
            new Message(),
            new Nuke(),
            new FriendlyCreeper(),
            new Hacker(),
            new GuardianJumpscare(),
            new FatBoy(),
            new SeeInventory(),
            new Cloud(),
            new ServerHacked(),
            new InvisibleSpider(),
            new AggressiveWolf(),
            new BigCactus(),
            new FakeDeath(),
            new FakeDamage(),
            new EndScreen(),
            new DemoScreen(),
            new LoadingScreen(),
            new FakeCreative(),
            new FakeHunger(),
            new ChangeHeldSlot(),
            new FakeExperience(),
            new NormalRandomTeleport(),
            new RandomRotate(),
            new HeadDance(),
            new SnowGolemEffect(),
            new FlamingBootsEffect(),
            new NoGravity(),
            new EggShooter(),
            new Spam(),
            new Venom(),
            new WaterCage(),
            new FlowerBootsEffect(),
            new RollingHeldSlot(),
            new Alone(),
            new BugChunk()
    };


    @Override
    public void onEnable() {
        loadWithListener(new Control());
        loadWithListener(new Freeze());
        api = new G5APIImpl(this);
        trl = this;
        new TrollCmd(this);
        // https://bstats.org/signatures/bukkit/G5Troll.svg
        int pluginId = 18817;
        Metrics metrics = new Metrics(this, pluginId);
        getServer().getPluginManager().registerEvents(this, this);
        FileConfiguration config = this.getConfig();
        for (Troll t : getTrolls()) {
            config.addDefault("changer." + t.getName() + ".name", t.getName());
            config.addDefault("changer." + t.getName() + ".description", t.getDesc());
            config.addDefault("changer." + t.getName() + ".usage", t.getUsage());
            config.addDefault("changer." + t.getName() + ".icon", t.getIcon().toString());
            int i = 1;
            for (String str : t.getArgs()) {
                config.addDefault("changer." + t.getName() + ".default_arg" + i, str);
                i++;
            }
        }
        config.options().copyDefaults(true);
        this.saveConfig();
    }

    public void loadWithListener(Troll t) {
        Bukkit.getServer().getPluginManager().registerEvents((Listener) t, this);
        addTroll(t);
    }

    public G5API getAPI() {
        return api;
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        loadChanger();
        writeInfo(Bukkit.getConsoleSender());
    }

    public int getTrollCount() {
        return getTrolls().length;
    }

    int spigotID = 110652;
    public void writeInfo(CommandSender sender) {
        if (!isEnabled()) {
            sender.sendMessage(ChatColor.RED + "G5Troll is disabled.");
        } else {
            sender.sendMessage(ChatColor.GREEN + "G5Troll is enabled.");
            sender.sendMessage(ChatColor.YELLOW + "Stats:");
            sender.sendMessage(ChatColor.YELLOW + "TrollCount: " + ChatColor.AQUA + getTrollCount());
            new UpdateChecker(this, spigotID).getVersion(version -> {
                sender.sendMessage(ChatColor.YELLOW + "Your G5Troll Version: " + ChatColor.AQUA + getDescription().getVersion());
                sender.sendMessage(ChatColor.YELLOW + "Newest G5Troll Version: " + ChatColor.AQUA + version);

            });
        }
    }


    public void loadChanger() {
        for (String st : getConfig().getConfigurationSection("changer").getKeys(false)) {
            Troll troll = getTrollByName(st);
            if (troll != null) {
                for (String str : getConfig().getConfigurationSection("changer." + st).getKeys(false)) {
                    if (str.equalsIgnoreCase("name")) {
                        troll.setName(getConfig().getString("changer." + st + ".name"));
                    }
                    if (str.equalsIgnoreCase("description")) {
                        troll.setDesc(getConfig().getString("changer." + st + ".description"));
                    }
                    if (str.equalsIgnoreCase("usage")) {
                        troll.setUsage(getConfig().getString("changer." + st + ".usage"));
                    }
                    if (str.equalsIgnoreCase("icon")) {
                        troll.setIcon(Material.valueOf(getConfig().getString("changer." + st + ".icon").toUpperCase()));
                    }
                    if (str.startsWith("default_arg")) {
                        try {
                            int arg = Integer.parseInt(str.replaceFirst("default_arg", ""));
                            troll.setArg(arg-1, getConfig().getString("changer." + st + ".default_arg" + arg));
                        } catch (Exception ignored) {}
                    }
                }
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[G5Changer] " + st + " loaded");
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
