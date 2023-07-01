package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class FatBoy extends Troll {
    public FatBoy() {
        super("FatBoy", "the player gets heavier", new String[]{"15"});
        setIcon(Material.ANVIL);
        setUsage("/troll execute FatBoy {player} {seconds}");
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int finalA = a*4;
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
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 6, 0, true, false));
                    p.setVelocity(p.getVelocity().add(new Vector(0,-0.25,0)));
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);
    }
}
