package pl.kubag5.g5troll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import pl.kubag5.g5troll.trolls.Troll;
import pl.kubag5.g5troll.trolls.TrollEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TrollCmd implements CommandExecutor {

    G5Troll main;
    public TrollCmd(G5Troll main) {
        main.getCommand("troll").setExecutor(this);
        main.getCommand("troll").setTabCompleter(new TrollCmdTabCompleter(main));
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String arg1 = args[0];
            if (arg1.equalsIgnoreCase("execute")) {
                if (args.length > 2) {
                    Troll troll = main.getTrollByName(args[1]);
                    if (troll != null) {
                        ArrayList<String> listaArrayList = new ArrayList<>(Arrays.asList(args));
                        listaArrayList.remove(0);
                        listaArrayList.remove(0);
                        try {
                            TrollEvent te = new TrollEvent((Player) sender, listaArrayList.toArray(new String[listaArrayList.size()]));
                            if (te.verify()) {
                                troll.execute(te);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.g5CnfTech("general.executed", troll)));
                            } else {
                                sender.sendMessage(main.g5CnfTech("general.offlineError", troll));
                            }
                        } catch (Exception ex) {
                            if (sender instanceof Player) {
                                sender.sendMessage(ChatColor.RED + "OtherError");
                                ex.printStackTrace();
                            } else {
                                sender.sendMessage(ChatColor.RED + "You cant execute trolls in console.... sorry.");
                            }
                        }
                    }
                }
            }
            if (arg1.equalsIgnoreCase("executeAs")) {
                if (args.length > 3) {
                    Troll troll = main.getTrollByName(args[2]);
                    if (troll != null) {
                        ArrayList<String> listaArrayList = new ArrayList<>(Arrays.asList(args));
                        listaArrayList.remove(0);
                        listaArrayList.remove(0);
                        listaArrayList.remove(0);
                        try {
                            TrollEvent te = new TrollEvent(Bukkit.getPlayer(args[1]), listaArrayList.toArray(new String[listaArrayList.size()]));
                            if (te.verify()) {
                                troll.execute(te);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.g5CnfTech("general.executed", troll)));
                            } else {
                                sender.sendMessage(main.g5CnfTech("general.offlineError", troll));
                            }
                        } catch (Exception ex) {
                            if (sender instanceof Player) {
                                sender.sendMessage(ChatColor.RED + "OtherError");
                                ex.printStackTrace();
                            } else {
                                sender.sendMessage(ChatColor.RED + "You cant execute trolls in console.... sorry.");
                            }
                        }
                    }
                }
            }
            if (arg1.equalsIgnoreCase("check")) {
                if (args.length > 1) {
                    Troll troll = main.getTrollByName(args[1]);
                    if (troll != null) {
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.GOLD + troll.getName());
                        sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + troll.getDesc());
                        sender.sendMessage(ChatColor.GREEN + "Default args: " + ChatColor.GOLD + Arrays.toString(troll.getArgs()));
                        sender.sendMessage(ChatColor.GREEN + "Args length: " + ChatColor.GOLD + troll.getArgsLength());
                        sender.sendMessage(ChatColor.GREEN + "Usage: " + ChatColor.GOLD + troll.getUsage());
                        sender.sendMessage(ChatColor.GREEN + "Icon: " + ChatColor.GOLD + troll.getIcon());
                    }
                }
            }
            if (arg1.equalsIgnoreCase("stopAllTrolls")) {
                if (args.length > 1) {
                   Player p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        stopAllTrolls(p,sender);
                    } else {
                        sender.sendMessage(main.g5CnfTech("general.offlineError"));
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "use: /troll stopAllTrolls <player> "  + ChatColor.RED + "[may cause lag] [stops all trolls and refreshes chunks at the specified <player>'s location.]");
                }
            }
            if (arg1.equalsIgnoreCase("menu")) {
                if (sender instanceof Player) {
                    Collection<? extends Player> playersCollection = Bukkit.getOnlinePlayers();
                    G5GUI gui = new G5GUI(G5Troll.getInstance().g5CnfTech("general.choosePlayer"), playersCollection.toArray(new Player[playersCollection.size()]));
                    Bukkit.getPluginManager().registerEvents(gui, G5Troll.getInstance());
                    gui.open((Player) sender);

                }
            }
            if (arg1.equalsIgnoreCase("info")) {
                G5Troll.getInstance().writeInfo(sender);
            }
            if (arg1.equalsIgnoreCase("reload")) {
               G5Troll.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.RED + "kubag5" + ChatColor.GRAY + " >>> " + ChatColor.GREEN + "G5Troll config reloaded");
               G5Troll.getInstance().loadChanger();
                sender.sendMessage(ChatColor.RED + "kubag5" + ChatColor.GRAY + " >>> " + ChatColor.GREEN + "changer reloaded");
            }
        } else {
            if (sender instanceof Player) {
                Collection<? extends Player> playersCollection = Bukkit.getOnlinePlayers();
                G5GUI gui = new G5GUI(G5Troll.getInstance().g5CnfTech("general.choosePlayer"), playersCollection.toArray(new Player[playersCollection.size()]));
                Bukkit.getPluginManager().registerEvents(gui, G5Troll.getInstance());
                gui.open((Player) sender);

            }
        }
        return false;
    }

    public void stopAllTrolls(Player p, CommandSender exe) {
        exe.sendMessage(ChatColor.RED + "Executing stopAllTrolls..." + ChatColor.DARK_RED + "[May cause lag :/]");
        int a = 0;
        for (BukkitTask task : Bukkit.getScheduler().getPendingTasks()) {
            Plugin taskPlugin = task.getOwner();
            if (taskPlugin.getName().equalsIgnoreCase("G5Troll")) {
                Bukkit.getScheduler().cancelTask(task.getTaskId());
                a++;
            }
        }
        exe.sendMessage(ChatColor.GOLD+""+a+ChatColor.GREEN + " stopped tasks.");
        p.closeInventory();
        exe.sendMessage(ChatColor.GREEN + "inventory closed for " + p.getName());

        int b = 0;
        int viewDistance = Bukkit.getViewDistance();
        try {
            viewDistance = p.getViewDistance();
        } catch (NoSuchMethodError ignored) {}
        if (viewDistance > 16) viewDistance = 16;
        World world = p.getWorld();
        Chunk playerChunk = p.getLocation().getChunk();

        int chunkX = playerChunk.getX();
        int chunkZ = playerChunk.getZ();

        for (int x = chunkX - viewDistance; x <= chunkX + viewDistance; x++) {
            for (int z = chunkZ - viewDistance; z <= chunkZ + viewDistance; z++) {
                Chunk chunk = world.getChunkAt(x, z);
                if (chunk.isLoaded()) {
                    p.getWorld().refreshChunk(chunk.getX(), chunk.getZ());
                    b++;
                }
            }
        }
        exe.sendMessage(ChatColor.GOLD+""+b+ChatColor.GREEN + " refreshed chunks");
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 10));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 10));
        p.setExp(p.getExp());
        exe.sendMessage(ChatColor.GREEN + p.getName() +" healed");
        exe.sendMessage(ChatColor.GREEN + "stopAllTrolls completed");
    }
}
