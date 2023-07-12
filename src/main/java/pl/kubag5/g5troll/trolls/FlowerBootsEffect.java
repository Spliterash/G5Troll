package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FlowerBootsEffect extends Troll {

    public FlowerBootsEffect() {
        super("FlowerBootsEffect", "Creates flowers under the player.", "10");
        setIcon(Material.RED_TULIP);
        setUsage("/troll execute FlowerBootsEffect {player} {seconds}");
        setShowWorldWarning(true);
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
                    if (p.getLocation().getBlock().getType() == Material.AIR) {
                        if (p.getLocation().add(new Vector(0,-1,0)).getBlock().getType() != Material.AIR) {
                            p.getLocation().getBlock().setType(getRandomFlowerMaterial());
                        }
                    }
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);
    }

    private Random random = new Random();
    private Material getRandomFlowerMaterial() {
        int index = random.nextInt(flowerMaterials.size());
        return flowerMaterials.get(index);
    }

    private final List<Material> flowerMaterials = Arrays.asList(
            Material.DANDELION,
            Material.POPPY,
            Material.BLUE_ORCHID,
            Material.ALLIUM,
            Material.AZURE_BLUET,
            Material.RED_TULIP,
            Material.ORANGE_TULIP,
            Material.WHITE_TULIP,
            Material.PINK_TULIP,
            Material.OXEYE_DAISY
    );
}
