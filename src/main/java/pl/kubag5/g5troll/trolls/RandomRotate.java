package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomRotate extends Troll {
    public RandomRotate() {
        super("RandomRotate", "Randomly rotates the player");
        setUsage("/troll execute RandomRotate {player}");
        setIcon(Material.CAKE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        Random random = new Random();
        utils.setRotation(p,random.nextInt(361)-180, random.nextInt(180)-90);
    }
}
