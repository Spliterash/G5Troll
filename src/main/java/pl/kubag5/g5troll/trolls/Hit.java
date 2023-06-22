package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Hit extends Troll {
    public Hit() {
        super("Hit", "Hit player", null);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        p.attack(p);
    }
}
