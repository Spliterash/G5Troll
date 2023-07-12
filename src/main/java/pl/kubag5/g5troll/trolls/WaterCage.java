package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WaterCage extends Troll {
    public WaterCage() {
        super("WaterCage", "Drown a player in this trap.");
        setIcon(Material.WATER_BUCKET);
        setShowWorldWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        p.getLocation().add(new Vector(0,-1,0)).getBlock().setType(Material.OBSIDIAN);

        p.getLocation().add(new Vector(1,0,0)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(-1,0,0)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(0,0,1)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(0,0,-1)).getBlock().setType(Material.OBSIDIAN);


        p.getLocation().add(new Vector(1,1,0)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(-1,1,0)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(0,1,1)).getBlock().setType(Material.OBSIDIAN);
        p.getLocation().add(new Vector(0,1,-1)).getBlock().setType(Material.OBSIDIAN);

        p.getLocation().add(new Vector(0,2,0)).getBlock().setType(Material.OBSIDIAN);


        p.getLocation().add(new Vector(0,1,0)).getBlock().setType(Material.WATER);
        p.teleport(p.getLocation().getBlock().getLocation().add(0.5,0,0.5));
    }
}
