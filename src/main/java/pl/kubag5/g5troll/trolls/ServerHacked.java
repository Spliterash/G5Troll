package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

public class ServerHacked extends Troll {
    public ServerHacked() {
        super("ServerHacked", "Fake Hacker Attack", null);
        setIcon(Material.BOW);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        int i = 0;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (i>30) break; i++;
            p.sendMessage(ChatColor.RED + "kubag5 [VIRUS SYSTEM] >>> " + ChatColor.AQUA + "hacking: " + ChatColor.GOLD + pl.getName());
        }
        p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1.0f, 0.0f);
        p.sendTitle("SERVER HACKED", "ez");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.kickPlayer(ChatColor.RED + "SERVER HACKED");
            }
        }, 50L);

    }
}
