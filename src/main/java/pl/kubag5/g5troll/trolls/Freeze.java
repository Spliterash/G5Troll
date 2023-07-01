package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.kubag5.g5troll.G5Troll;

import java.util.ArrayList;

public class Freeze extends Troll implements Listener {
    public Freeze() {
        super("Freeze", "Freeze player", new String[]{"10"});
        setUsage("/troll execute Firework {player} {seconds}");
        setIcon(Material.ICE);
    }

    ArrayList<Player> freezeList = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (freezeList.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int arg2 = Integer.parseInt(getArg(0));
        try {
            arg2 = Integer.parseInt(args[1]);
        } catch (Exception ignored) {
        }
        if (freezeList.contains(p)) return;
        freezeList.add(p);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
            if (p != null) {
                freezeList.remove(p);
            }
        }, arg2 * 20L);
    }
}
