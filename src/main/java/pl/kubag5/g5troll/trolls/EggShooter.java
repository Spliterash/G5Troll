package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class EggShooter extends Troll {
    public EggShooter() {
        super("EggShooter", "Shoots eggs into the air.", "10", "5");
        setUsage("/troll execute NoGravity {player} {seconds} {GTech1-speed}");
        setIcon(Material.EGG);
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
        int finalA = a;
        int finalB = b;
        Random random = new Random();
        BukkitRunnable task = new BukkitRunnable() {
            int c = finalA*utils.calcIntGTech1SpeedB(finalB);
            @Override
            public void run() {
                if (c < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    c--;
                }
                if (p != null) {
                    Egg egg = p.launchProjectile(Egg.class);
                    double x = random.nextDouble() - 0.5;
                    double y = random.nextDouble();
                    double z = random.nextDouble() - 0.5;
                    Vector direction = new Vector(x, y, z).normalize();
                    egg.setVelocity(direction);
                    p.playSound(p.getLocation(), Sound.ENTITY_EGG_THROW, 1,1);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), utils.calcIntGTech1SpeedA(finalB), utils.calcIntGTech1SpeedA(finalB));
    }
}
