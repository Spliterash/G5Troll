package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Nuke extends Troll {
    public Nuke() {
        super("Nuke", "Creates explosion.", "20");
        setUsage("/troll execute Nuke {player} {power}");
        setIcon(Material.FIRE_CHARGE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        p.getWorld().createExplosion(p.getLocation(), a, true);
    }
}
