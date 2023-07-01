package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FriendlyCreeper extends Troll {
    public FriendlyCreeper() {
        super("FriendlyCreeper", "Spawn creeper with 0 explosion radius.", new String[]{"1"});
        setUsage("/troll execute FriendlyCreeper {player} {count}");
        setIcon(Material.CREEPER_HEAD);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        for (int i = 0; i < a; i++) {
            Creeper creeper = (Creeper) p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
            creeper.setExplosionRadius(0);
            creeper.setPowered(true);
        }
    }
}
