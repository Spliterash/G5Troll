package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GuardianJumpscare extends Troll {
    public GuardianJumpscare() {
        super("GuardianJumpscare", "Guardian Jumpscare... :)", null);
        setIcon(Material.GLOWSTONE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0f, 0.0f);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 20, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 20, true, false));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "particle minecraft:elder_guardian " +  + p.getLocation().getBlockX() + " "  + p.getLocation().getBlockY() +  " " + p.getLocation().getBlockZ() + " 0 0 0 1 1 force " + p.getName());
    }
}
