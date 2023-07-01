package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Firework extends Troll {

    public Firework() {
        super("Firework", "Launch the player into the air!", new String[]{"3.5"});
        setUsage("/troll execute Firework {player} {power}");
        setIcon(Material.FIREWORK_ROCKET);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        if (args.length > 1)  {
            try {
                p.setVelocity(new Vector(0,Double.parseDouble(args[1]),0));
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        } else {
            p.setVelocity(new Vector(0,Double.parseDouble(getArg(0)),0));
        }
    }
}
