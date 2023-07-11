package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

public class Alone extends Troll{
    public Alone() {
        super("Alone", "Make every other player invisible", "30");
        setUsage("/troll execute Alone {player} {seconds}");
        setIcon(Material.WHEAT);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        for (Player pp : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(G5Troll.getInstance(), pp);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                p.showPlayer(G5Troll.getInstance(), pp);
            }, a*20L);
        }
    }
}
