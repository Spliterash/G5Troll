package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class AirShots extends Troll {
    public AirShots() {
        super("AirShots", "shoots arrows from the air.", new String[]{"10"});
        setUsage("/troll execute AirShots {player} {seconds}");
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a*4;
        Random random = new Random();
        BukkitRunnable task = new BukkitRunnable() {
            int aCC = finalA;
            @Override
            public void run() {
                if (aCC < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    aCC--;
                }
                if (p != null) {
                    Location playerLocation = p.getLocation();
                    World world = playerLocation.getWorld();
                    Location arrowLocation = new Location(world, playerLocation.getX() + random.nextInt(9)-4, playerLocation.getY()+7, playerLocation.getZ() + random.nextInt(9)-4);
                    Arrow arrow = (Arrow) world.spawnEntity(arrowLocation, EntityType.ARROW);
                    arrow.setVelocity(playerLocation.toVector().subtract(arrowLocation.toVector()).normalize().multiply(2.0));
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);
    }
}
