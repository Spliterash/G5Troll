package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Cobweb extends Troll {
    public Cobweb() {
        super("Cobweb", "slow down the player with the web");
        setIcon(Material.COBWEB);
        setShowWorldWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        fill3x3Air(p.getLocation(), Material.COBWEB);
    }


    public void fill3x3Air(Location center, Material blockType) {
        World world = center.getWorld();
        int centerX = center.getBlockX();
        int centerY = center.getBlockY();
        int centerZ = center.getBlockZ();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    int x = centerX + xOffset;
                    int y = centerY + yOffset;
                    int z = centerZ + zOffset;
                    Location location = new Location(world, x, y, z);
                    Block block = location.getBlock();
                    if (block.getType() == Material.AIR) {
                        block.setType(blockType);
                    }
                }
            }
        }
    }
}
