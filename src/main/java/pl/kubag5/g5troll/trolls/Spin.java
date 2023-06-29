package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;
import pl.kubag5.g5troll.Utils;


public class Spin extends Troll {
    public Spin() {
        super("Spin", "Spin the player!", new String[]{"6", "3"});
        setUsage("/troll execute Spin {player} {seconds} {power}");
        setIcon(Material.GOLDEN_APPLE);
    }
    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        int arg2 = Integer.parseInt(getArg(0));
        try {
        arg2 = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int count = 20 * arg2;
        int spinning = Integer.parseInt(getArg(1));
        try {
            spinning = Integer.parseInt(args[2]);
        } catch (Exception ignored) {}

        int finalSpinning = spinning;
        while (count > 0) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                if (p != null) {
                   utils.setRotation(p,p.getLocation().getYaw() + finalSpinning, p.getLocation().getPitch());
                }
            },count);
            count--;
        }
    }
}
