package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Rotate extends Troll {
    public Rotate() {
        super("Rotate", "rotates the player", new String[]{"90", "0"});
        setUsage("/troll execute Rotate {player} {YAW} {PITCH}");
        setIcon(Material.APPLE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);

        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}

        int b = Integer.parseInt(getArg(1));
        try {
            b = Integer.parseInt(args[2]);
        } catch (Exception ignored) {}

        utils.setRotation(p,p.getLocation().getYaw() + a, p.getLocation().getPitch() + b);
    }
}
