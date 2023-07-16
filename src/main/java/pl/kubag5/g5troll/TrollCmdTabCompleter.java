package pl.kubag5.g5troll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.kubag5.g5troll.trolls.Troll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrollCmdTabCompleter implements TabCompleter {
    G5Troll main;

    public TrollCmdTabCompleter(G5Troll main) {
        this.main = main;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add("execute");
            list.add("executeAs");
            list.add("check");
            list.add("menu");
            list.add("reload");
            list.add("info");
            list.add("stopAllTrolls");
            return list;
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("execute") || args[0].equalsIgnoreCase("check")) {
                if (args[1] == null) {
                    return main.getTrollArrayList();
                } else {
                    return main.getTrollArrayListStartedWith(args[1]);
                }
            }
            if (args[0].equalsIgnoreCase("executeAs")) {
                ArrayList<String> list = new ArrayList<>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    list.add(p.getName());
                }
                return list;
            }
        }
        if (args.length == 3) {
                if (args[0].equalsIgnoreCase("execute")) {
                    Troll troll = main.getTrollByName(args[1]);
                    if (troll != null) {
                        if (troll.warningsExist() && Objects.equals(args[2], "")) {
                            sender.sendMessage(main.g5CnfTech("general.warn", troll));
                        }
                    }
                }
            if (args[0].equalsIgnoreCase("executeAs")) {
                if (args[2] == null) {
                    return main.getTrollArrayList();
                } else {
                    return main.getTrollArrayListStartedWith(args[2]);
                }
            }
        }
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("executeAs")) {
                Troll troll = main.getTrollByName(args[2]);
                if (troll != null) {
                    if (troll.warningsExist() && Objects.equals(args[3], "")) {
                        sender.sendMessage(main.g5CnfTech("general.warn", troll));
                    }
                }
            }
        }
        if (args.length > 3) {
            if (args[0].equalsIgnoreCase("execute")) {
                ArrayList<String> list = new ArrayList<>();
                try {
                    Troll troll = main.getTrollByName(args[1]);
                    String hint = troll.getArg(args.length - 4);
                    if (hint.equalsIgnoreCase("{player}")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            list.add(p.getName());
                        }
                    } else {
                        list.add(hint);
                    }
                } catch (Exception ignored) {
                }

                return list;
            }
        }
        if (args.length > 4) {
            if (args[0].equalsIgnoreCase("executeAs")) {
                ArrayList<String> list = new ArrayList<>();
                try {
                    String hint = main.getTrollByName(args[2]).getArg(args.length - 5);
                    if (hint.equalsIgnoreCase("{player}")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            list.add(p.getName());
                        }
                    } else {
                        list.add(hint);
                    }
                } catch (Exception ignored) {
                }

                return list;
            }
        }
        return null;
    }
}
