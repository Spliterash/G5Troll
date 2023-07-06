package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;


public class HeadDance extends Troll {
    public HeadDance() {
        super("HeadDance", "Randomly rotates the player head for x seconds.", "5");
        setUsage("/troll execute HeadDance {player} {seconds}");
        setIcon(Material.ZOMBIE_HEAD);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a*10;
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
                    utils.setRotation(p,random.nextInt(361)-180, random.nextInt(180)-90);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 2, 2);
    }
}
