package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

public class Back extends Troll {
    public Back() {
        super("Back", "Return the player to his old location.", "2");
        setUsage("/troll execute Back {player} {seconds}");
        setIcon(Material.BONE_MEAL);
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
        Location lt = p.getLocation();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
            if (p.isOnline()) {
                p.teleport(lt);
            }
        }, arg2 * 20L);
    }
}
