package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.kubag5.g5troll.G5Troll;

public class FalseAntiCheatBan extends Troll {
    public FalseAntiCheatBan() {
        super("FalseAntiCheatBan", "backs the player 10 times and then kick them out.");
        setIcon(Material.COMMAND_BLOCK);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        Location loc = p.getLocation();
        BukkitRunnable task = new BukkitRunnable() {
            int a = 10;
            @Override
            public void run() {
                a--;
                if (a < 0) {
                    Bukkit.getScheduler().cancelTask(getTaskId());
                    p.kickPlayer(ChatColor.RED + "cheating");
                }
                if (p.isOnline()) {
                    p.teleport(loc);
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 5, 5);

    }
}
