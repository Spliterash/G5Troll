package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import pl.kubag5.g5troll.G5Troll;

public class AggressiveWolf extends Troll {
    public AggressiveWolf() {
        super("AggressiveWolf", "Spawn aggressive wolf", "1");
        setUsage("/troll execute AggressiveWolf {player} {count}");
        setIcon(Material.BONE);
        setShowKillWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        for (int i = 0; i < a; i++) {
            Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
            wolf.setAngry(true);
            wolf.setTarget(p);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> wolf.setSitting(false), 3L);
        }
    }
}
