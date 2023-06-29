package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import pl.kubag5.g5troll.G5Troll;

public class FakeTNT extends Troll {
    public FakeTNT() {
        super("FakeTNT", "Summon fake tnt next to player", null);
        setIcon(Material.TNT);
    }


    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(p.getLocation().add(0, 2, 0), EntityType.PRIMED_TNT);
        tnt.setFuseTicks(30 * 20);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
            tnt.remove();
        }, 4 * 20L);
    }
}
