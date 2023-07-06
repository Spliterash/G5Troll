package pl.kubag5.g5troll.trolls;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NormalRandomTeleport extends Troll {
    public NormalRandomTeleport() {
        super("NormalRandomTeleport", "Randomly teleports the player", "50");
        setUsage("/troll execute NormalRandomTeleport {player} {range}");
        setIcon(Material.ENDER_PEARL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        teleportToSafeLocation(p,a);
    }

    public void teleportToSafeLocation(Player player, int radius) {
        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        int centerX = playerLocation.getBlockX();
        int centerZ = playerLocation.getBlockZ();
        int minX = centerX - radius;
        int minZ = centerZ - radius;
        int maxX = centerX + radius;
        int maxZ = centerZ + radius;
        int randomX = minX + (int) (Math.random() * ((maxX - minX) + 1));
        int randomZ = minZ + (int) (Math.random() * ((maxZ - minZ) + 1));
        int y = world.getHighestBlockYAt(randomX, randomZ);
        player.teleport(new Location(player.getWorld(),randomX+0.5,y+2,randomZ+0.5));
    }
}
