package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

public class Funeral extends Troll {
    public Funeral() {
        super("Funeral", "Knocks the player to the ground.", null);
        setIcon(Material.SAND);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        for (int i = 0; i < 10; i++) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                if (p != null) {
                    if (p.getLocation().getBlock().getType().isAir()) {
                        p.teleport(p.getLocation().add(new Vector(0,-1.5,0)));
                    } else {
                        p.teleport(p.getLocation().add(new Vector(0,-0.1,0)));
                    }
                    p.spawnParticle(Particle.ASH, p.getLocation(), 500, 1, 1,1);
                }
            },i * 10L);
        }
    }
}
