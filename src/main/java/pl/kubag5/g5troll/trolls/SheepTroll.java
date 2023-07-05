package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.Random;

public class SheepTroll extends Troll {
    public SheepTroll() {
        super("Sheep", "spawns sheep with a random color.", "5");
        setUsage("/troll execute Sheep {player} {sheepCount}");
        setIcon(Material.WHITE_WOOL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        Random random = new Random();
        for (int i = 0; i < a; i++) {
            Sheep sheep = (Sheep) p.getWorld().spawnEntity(p.getLocation(), EntityType.SHEEP);
            DyeColor randomColor = DyeColor.values()[random.nextInt(DyeColor.values().length)];
            sheep.setColor(randomColor);
        }
    }
}
