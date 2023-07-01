package pl.kubag5.g5troll.trolls;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class Hacker extends Troll {
    public Hacker() {
        super("Hacker", "The player becomes a cheater.", new String[]{"10"});
        setIcon(Material.DIAMOND_SWORD);
        setUsage("/troll execute Hacker {player} {seconds}");
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a;
        BukkitRunnable task = new BukkitRunnable() {
            int c = finalA*4;
            Random random = new Random();
            @Override
            public void run() {
                if (c < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    c--;
                }
                if (p != null) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6, 3));
                    if (random.nextInt(10) == 3) p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 6, 0));
                    if (random.nextInt(5) == 3) p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 6, 1));
                    if (random.nextInt(60) == 3) p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6, 0));
                    p.spawnParticle(Particle.FLAME, p.getLocation(), 30, 1,1,1);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);
    }
}
