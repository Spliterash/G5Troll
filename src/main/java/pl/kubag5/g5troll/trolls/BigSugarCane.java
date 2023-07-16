package pl.kubag5.g5troll.trolls;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BigSugarCane extends Troll {
    public BigSugarCane() {
        super("BigSugarCane", "Creates a sugar cane all the way to the top");
        setIcon(Material.SUGAR_CANE);
        setShowWorldWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        Location startLocation = event.getTarget().getLocation();
        World world = startLocation.getWorld();
        int startX = startLocation.getBlockX();
        int startY = startLocation.getBlockY();
        int startZ = startLocation.getBlockZ();

        world.getBlockAt(startX, startY-1, startZ-1).setType(Material.WATER);
        world.getBlockAt(startX, startY-1, startZ).setType(Material.DIRT);

        for (int y = startY; y <= world.getMaxHeight(); y++) {
            Location currentLocation = new Location(world, startX, y, startZ);
            Block block = world.getBlockAt(currentLocation);
            if (block.getType() == Material.AIR) {
                block.setType(Material.SUGAR_CANE);
            } else {
                break;
            }
        }

    }
}
