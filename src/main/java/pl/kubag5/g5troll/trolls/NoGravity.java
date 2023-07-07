package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

public class NoGravity extends Troll {

    public NoGravity() {
        super("NoGravity", "The player will lose their gravity.", "10");
        setIcon(Material.PAPER);
        setUsage("/troll execute NoGravity {player} {seconds}");
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a;
        BukkitRunnable task = new BukkitRunnable() {
            int c = finalA*4;
            @Override
            public void run() {
                if (c < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    c--;
                }
                if (p != null) {
                   p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 6,-1));
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);
    }
}
