package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Random;

public class FakeDiamondOre extends Troll {
    public FakeDiamondOre() {
        super("FakeDiamondOre", "changes stone to diamond ore", "25", "50");
        setUsage("/troll execute FakeDiamondOre {player} {range} {percent} ");
        setIcon(Material.DIAMOND_ORE);
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
        convertToDiamondOre(p,a,b);
    }

    private void convertToDiamondOre(Player p, int range, int percent) {
        int centerX = p.getLocation().getBlockX();
        int centerY = p.getLocation().getBlockY();
        int centerZ = p.getLocation().getBlockZ();
        World world = p.getLocation().getWorld();
        Random rnd = new Random();
        for (int x = centerX - range; x <= centerX + range; x++) {
            for (int y = centerY - range; y <= centerY + range; y++) {
                for (int z = centerZ - range; z <= centerZ + range; z++) {
                    Block currentBlock = world.getBlockAt(x, y, z);
                    if (currentBlock.getType() == Material.STONE) {
                        if (rnd.nextInt(101) < percent) {
                            p.sendBlockChange(currentBlock.getLocation(), Material.DIAMOND_ORE.createBlockData());
                        }
                    }
                }
            }
        }
    }
}
