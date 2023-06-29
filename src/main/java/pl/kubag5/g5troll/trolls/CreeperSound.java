package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CreeperSound extends Troll {
    public CreeperSound() {
        super("CreeperSound", "plays creeper sound next to player", null);
        setIcon(Material.CREEPER_HEAD);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        p.playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1.0f, 1.0f);
    }
}
