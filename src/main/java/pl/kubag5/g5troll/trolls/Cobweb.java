package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Cobweb extends Troll {
    public Cobweb() {
        super("Cobweb", "slow down the player with the web");
        setIcon(Material.COBWEB);
        setShowWorldWarning(true);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        utils.fill3x3Air(p.getLocation(), Material.COBWEB);
    }



}
