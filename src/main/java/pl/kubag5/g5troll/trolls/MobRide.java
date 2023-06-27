package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MobRide extends Troll {
    public MobRide() {
        super("MobRide", "the player will start riding the mob.", new String[]{"COW"});
        setUsage("/troll execute MobRide {player} {MobType}");
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        try {
            Entity mob = p.getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(args[1].toUpperCase()));
            mob.addPassenger(p);
        } catch (Exception exception) {
            Entity mob = p.getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(getArg(0).toUpperCase()));
            mob.addPassenger(p);
        }
    }
}
