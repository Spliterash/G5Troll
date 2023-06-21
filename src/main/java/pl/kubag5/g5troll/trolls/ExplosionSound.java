package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ExplosionSound extends Troll {
    public ExplosionSound() {
        super("ExplosionSound", "plays explosion sound next to player", null);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }
}
