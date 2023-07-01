package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Swap extends Troll {
    public Swap() {
        super("Swap", "swaps 2 players positions.", new String[]{"{player}"});
        setActiveInMenu(false);
        setIcon(Material.COD);
        setUsage("/troll execute Swap {player} {player}");
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        Player p2 = Bukkit.getPlayer(args[1]);
        Location loc = p.getLocation();
        p.teleport(p2.getLocation());
        p2.teleport(loc);
    }
}
