package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.kubag5.g5troll.G5Troll;

import java.util.ArrayList;


public class Control extends Troll implements Listener {
    public Control() {
        super("Control", "Control player.");
        setIcon(Material.COMPASS);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for (Player exe : executors) {
            e.getPlayer().hidePlayer(G5Troll.getInstance(), exe);
        }
    }

    ArrayList<Player> executors = new ArrayList<>();

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        Player exe = event.getExecutor();
        if (exe == p) {
            exe.sendMessage(ChatColor.RED + "You can't control yourself.");
            return;
        }
        if (executors.contains(exe)) {
            exe.sendMessage(ChatColor.RED + "You can't control 2 people at the same time.");
            return;
        }
        String mess = ChatColor.RED + "Press shift to stop control troll.";
        exe.sendMessage(mess);
        exe.teleport(p);
        exe.hidePlayer(G5Troll.getInstance(), p);
        for (Player pp : Bukkit.getOnlinePlayers()) {
            pp.hidePlayer(G5Troll.getInstance(), exe);
        }
        executors.add(exe);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (p.isOnline() && exe.isOnline() && !exe.isSneaking() && !p.isDead() && !exe.isDead()) {
                    p.teleport(exe);
                    p.setSprinting(exe.isSprinting());
                } else {
                    executors.remove(exe);
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                    if (exe.isOnline()) {
                        exe.sendMessage(ChatColor.GREEN + "Control stopped.");
                        for (Player pp : Bukkit.getOnlinePlayers()) {
                            pp.showPlayer(G5Troll.getInstance(), exe);
                        }
                    }
                    if (exe.isOnline() && p.isOnline()) {
                        exe.showPlayer(G5Troll.getInstance(), p);
                    }
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), 1, 1);
    }
}
