package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Lightning extends Troll {
    public Lightning() {
        super("Lightning", "Hit player using lightning", null);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        p.getWorld().strikeLightning(p.getLocation());
    }
}
