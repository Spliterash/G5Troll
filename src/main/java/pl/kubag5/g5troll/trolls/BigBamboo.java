package pl.kubag5.g5troll.trolls;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BigBamboo extends Troll {
    public BigBamboo() {
        super("BigBamboo", "Creates a bamboo all the way to the top");
        setIcon(Material.BAMBOO);
        setShowWorldWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        Location startLocation = event.getTarget().getLocation();
        World world = startLocation.getWorld();
        int startX = startLocation.getBlockX();
        int startY = startLocation.getBlockY();
        int startZ = startLocation.getBlockZ();
        world.getBlockAt(startX, startY-1, startZ).setType(Material.DIRT);
        for (int y = startY; y <= world.getMaxHeight(); y++) {
            Location currentLocation = new Location(world, startX, y, startZ);
            Block block = world.getBlockAt(currentLocation);
            if (block.getType() == Material.AIR) {
                block.setType(Material.BAMBOO);
            } else {
                break;
            }
        }

    }
}
