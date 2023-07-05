package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Anvil extends Troll {
    public Anvil() {
        super("Anvil", "Drop an anvil on a player", "20");
        setUsage("/troll execute Anvil {player} {height}");
        setIcon(Material.ANVIL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        p.getLocation().add(new Vector(0,a,0)).getBlock().setType(Material.ANVIL);
    }


}
