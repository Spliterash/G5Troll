package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

public class Venom extends Troll {
    public Venom() {
        super("Venom", "Attracts all entity within the selected range.", "15", "10");
        setUsage("/troll execute Venom {player} {seconds} {range}");
        setIcon(Material.ENDER_EYE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int b = Integer.parseInt(getArg(1));
        try {
            b = Integer.parseInt(args[2]);
        } catch (Exception ignored) {}
        int finalB = b;
        int finalA = a;
        BukkitRunnable task = new BukkitRunnable() {
            int c = finalA*5;
            @Override
            public void run() {
                if (c < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    c--;
                }
                if (p != null) {
                    int c = 5;
                    for (Entity e : p.getNearbyEntities(finalB, finalB, finalB)) {
                        e.setVelocity(p.getLocation().toVector().subtract(e.getLocation().toVector()).normalize().multiply(0.4));
                        c += 5;
                    }
                    if (c > 100) c = 100;
                    p.spawnParticle(Particle.PORTAL, p.getLocation(), c, 0.5, 0.5,0.5, 0.01);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 4, 4);
    }
}
