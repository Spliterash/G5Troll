package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Spam extends Troll {
    public Spam() {
        super("Spam", "Spam player chat.", "&cYou are noob");
        setIcon(Material.GOLDEN_SHOVEL);
        setUsage("/troll execute Spam {player} [message]");
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        if (args.length != 1) {
            String str = "";
            for (int i = 1; i < args.length; i++) {
                str += args[i] + " ";
            }
            spam(str, p);
        } else {
            spam(getArg(0), p);
        }
    }

    public void spam(String mess, Player p) {
        for (int i = 0; i < 150; i++) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));
        }
    }
}
