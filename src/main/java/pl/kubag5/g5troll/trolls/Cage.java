package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Cage extends Troll {
    public Cage() {
        super("Cage", "Catch a player in a bedrock cage", null);
        setIcon(Material.BEDROCK);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        p.getLocation().add(new Vector(0,-1,0)).getBlock().setType(Material.BEDROCK);
        p.getLocation().add(new Vector(1,0,0)).getBlock().setType(Material.BEDROCK);
        p.getLocation().add(new Vector(-1,0,0)).getBlock().setType(Material.BEDROCK);
        p.getLocation().add(new Vector(0,0,1)).getBlock().setType(Material.BEDROCK);
        p.getLocation().add(new Vector(0,0,-1)).getBlock().setType(Material.BEDROCK);
        p.getLocation().add(new Vector(0,2,0)).getBlock().setType(Material.BEDROCK);
        p.teleport(p.getLocation());
    }
}
