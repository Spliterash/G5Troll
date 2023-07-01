package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Hit extends Troll {
    public Hit() {
        super("Hit", "Hit player", null);
        setIcon(Material.STONE_SWORD);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        p.attack(p);
    }
}
