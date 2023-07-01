package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AntiMiner extends Troll {
    public AntiMiner() {
        super("AntiMiner", "kicks the player out of the hole", null);
        setIcon(Material.GOLDEN_PICKAXE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        Location pl = p.getLocation();
        int y = 2 + pl.getWorld().getHighestBlockYAt(pl.getBlockX(), pl.getBlockZ());
        p.teleport(new Location(pl.getWorld(), pl.getBlockX() + 0.5, y, pl.getBlockZ() + 0.5));
    }
}
