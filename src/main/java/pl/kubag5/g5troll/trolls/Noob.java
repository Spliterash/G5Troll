package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Noob extends Troll {
    public Noob() {
        super("Noob", "creates a sign saying \"noob\"", null);
        setIcon(Material.OAK_SIGN);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        Location signloc = p.getLocation().add(new Vector(0,1,0));
        p.getWorld().setBlockData(signloc, Material.OAK_WALL_SIGN.createBlockData());
        Block c = p.getWorld().getBlockAt(signloc);
        BlockState state = c.getState();
        Sign sign = (Sign) state;
        sign.setLine(1, "NOOB");
        sign.setColor(DyeColor.RED);
        sign.setGlowingText(true);

        sign.update();
        utils.setRotation(p,0, 0);
    }
}
