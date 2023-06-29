package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Hunger extends Troll {

    public Hunger() {
        super("Hunger", "Set the food level.", new String[]{"0"});
        setUsage("/troll execute Hunger {player} {FoodLevel}");
        setIcon(Material.BREAD);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        p.setFoodLevel(a);
    }
}
