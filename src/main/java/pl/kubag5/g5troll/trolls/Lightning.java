package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Lightning extends Troll {
    public Lightning() {
        super("Lightning", "Hit player using lightning");
        setIcon(Material.YELLOW_WOOL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        p.getWorld().strikeLightning(p.getLocation());
    }
}
