package pl.kubag5.g5troll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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
            list.add("check");
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
        }
        if (args.length > 3) {
            if (args[0].equalsIgnoreCase("execute")) {
                ArrayList<String> list = new ArrayList<>();
                try {
                    list.add(main.getTrollByName(args[1]).getArg(args.length-4));
                } catch (Exception ignored) {}

                return list;
            }
        }
        return null;
    }
}
