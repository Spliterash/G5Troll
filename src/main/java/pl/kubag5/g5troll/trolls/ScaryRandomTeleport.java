package pl.kubag5.g5troll.trolls;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ScaryRandomTeleport extends Troll {
    public ScaryRandomTeleport() {
        super("ScaryRandomTeleport", "Teleports player with amazing effects", new String[]{"50"});
        setUsage("/troll execute ScaryRandomTeleport {player} {range}");
        setIcon(Material.BLACK_WOOL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);

        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        teleportToSafeLocation(p,a);
        p.playSound(p.getLocation(), Sound.ENTITY_CAT_HISS, 2.0f, 1.0f);
        p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 2.0f, 0.0f);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100,2, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100,5, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100,5, true, false));
    }

    public void teleportToSafeLocation(Player player, int radius) {
        World world = player.getWorld();
        Location playerLocation = player.getLocation();
        int centerX = playerLocation.getBlockX();
        int centerZ = playerLocation.getBlockZ();
        int minX = centerX - radius;
        int minZ = centerZ - radius;
        int maxX = centerX + radius;
        int maxZ = centerZ + radius;
        int randomX = minX + (int) (Math.random() * ((maxX - minX) + 1));
        int randomZ = minZ + (int) (Math.random() * ((maxZ - minZ) + 1));
        int y = world.getHighestBlockYAt(randomX, randomZ);
        player.teleport(new Location(player.getWorld(),randomX+0.5,y+2,randomZ+0.5));
    }
}
