package pl.kubag5.g5troll;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.kubag5.g5troll.trolls.Troll;

import java.util.ArrayList;
import java.util.Arrays;

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
                            troll.execute(listaArrayList.toArray(new String[listaArrayList.size()]));
                            sender.sendMessage(ChatColor.GOLD + troll.getName() + ChatColor.GREEN + " executed.");
                        } catch (Exception ex) {
                            sender.sendMessage(ChatColor.RED + "Error (is your victim online?)");
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
                    }
                }
            }
        }
        return false;
    }
}
