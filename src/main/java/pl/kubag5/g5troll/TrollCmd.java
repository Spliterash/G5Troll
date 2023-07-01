package pl.kubag5.g5troll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                            troll.execute(new TrollEvent((Player) sender, listaArrayList.toArray(new String[listaArrayList.size()])));
                            sender.sendMessage(ChatColor.GOLD + troll.getName() + ChatColor.GREEN + " executed.");
                        } catch (Exception ex) {
                            if (sender instanceof Player) {
                                sender.sendMessage(ChatColor.RED + "Error (is your victim online?)");
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
            if (arg1.equalsIgnoreCase("menu")) {
                if (sender instanceof Player) {
                    Collection<? extends Player> playersCollection = Bukkit.getOnlinePlayers();
                    G5GUI gui = new G5GUI(ChatColor.GREEN + "Choose player", playersCollection.toArray(new Player[playersCollection.size()]));
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
                G5GUI gui = new G5GUI(ChatColor.GREEN + "Choose player", playersCollection.toArray(new Player[playersCollection.size()]));
                Bukkit.getPluginManager().registerEvents(gui, G5Troll.getInstance());
                gui.open((Player) sender);

            }
        }
        return false;
    }
}
