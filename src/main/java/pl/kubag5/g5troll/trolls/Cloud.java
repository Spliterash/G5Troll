package pl.kubag5.g5troll.trolls;

import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class Cloud extends Troll {
    public Cloud() {
        super("Cloud", "Cloud will appear on the player", new String[]{"15"});
        setIcon(Material.WHITE_WOOL);
        setUsage("/troll execute Cloud {player} {seconds}");
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a*10;
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
                    p.spawnParticle(Particle.CLOUD, p.getLocation().add(new Vector(0,1.5,0)), 75, 0.2, 0.2,0.2, 0.1);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 2, 2);
    }
}
