package pl.kubag5.g5troll.trolls;


import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class BugChunk extends Troll implements Listener {
    public BugChunk() {
        super("BugChunk", "Blocks close to the player will cease to exist.");
        setIcon(Material.FLINT);
    }


    @Override
    public void execute(TrollEvent event) {
        Player player = event.getTarget();
        if (player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        Location location = player.getLocation();
        int playerX = location.getBlockX();
        int playerY = location.getBlockY();
        int playerZ = location.getBlockZ();

        for (int x = playerX - 10; x <= playerX + 10; x++) {
            for (int z = playerZ - 10; z <= playerZ + 10; z++) {
                for (int y = 0; y <= playerY + 2; y++) {
                    if (y == playerY - 1 && x == playerX && z == playerZ) {
                        continue;
                    }

                    Location blockLocation = new Location(location.getWorld(), x, y, z);
                    Block block = blockLocation.getBlock();
                    player.sendBlockChange(blockLocation, Material.AIR.createBlockData());
                }
            }
        }

    }
}
