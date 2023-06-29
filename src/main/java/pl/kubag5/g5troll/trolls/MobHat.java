package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MobHat extends Troll {
    public MobHat() {
        super("MobHat", "Places a entity on the player's head.", new String[]{"PIG"});
        setUsage("/troll execute MobHat {player} {MobType}");
        setIcon(Material.PIG_SPAWN_EGG);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        try {
            Entity mob = p.getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(args[1].toUpperCase()));
            p.addPassenger(mob);
        } catch (Exception exception) {
            Entity mob = p.getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(getArg(0).toUpperCase()));
            p.addPassenger(mob);
        }
    }
}
