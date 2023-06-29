package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Void extends Troll {
    public Void() {
        super("Void", "destroys all blocks under the player.", null);
        setIcon(Material.BEDROCK);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        int y = p.getLocation().getBlockY() + 3;
        int x = p.getLocation().getBlockX();
        int z = p.getLocation().getBlockZ();
        World world = p.getWorld();
        p.teleport(new Location(world, x+0.5, y-3, z+0.5));
        p.setVelocity(new Vector(0, -1, 0));
        while (y>world.getMinHeight()-1) {
            world.getBlockAt(new Location(world, x,y,z)).setType(Material.AIR);
            y--;
        }
    }
}
