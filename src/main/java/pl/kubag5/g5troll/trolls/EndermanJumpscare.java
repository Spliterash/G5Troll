package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

public class EndermanJumpscare extends Troll{
    public EndermanJumpscare() {
        super("EndermanJumpscare", "Enderman jumpscare... :)");
        setIcon(Material.ENDERMAN_SPAWN_EGG);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        Location playerLocation = p.getLocation();
        Vector direction = playerLocation.getDirection().normalize();
        double offsetX = direction.getX();
        double offsetY = direction.getY();
        double offsetZ = direction.getZ();
        double targetX = playerLocation.getX() + offsetX;
        double targetY = playerLocation.getY() + offsetY;
        double targetZ = playerLocation.getZ() + offsetZ;
        Location loc = new Location(playerLocation.getWorld(), targetX, targetY - 1, targetZ);
        loc.setYaw(p.getLocation().getYaw()-180);
        Enderman enderman = (Enderman) p.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
        enderman.setAI(false);
        try {
            enderman.setScreaming(true);
        } catch (NoSuchMethodError ex) {
            Bukkit.getConsoleSender().sendMessage("G5Troll can't use setScreaming() method");
        }
        enderman.setInvulnerable(true);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 2.0f, 0.0f);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 20, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 20, true, false));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), enderman::remove, 50L);
    }
}
