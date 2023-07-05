package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class BlockRain extends Troll {
    public BlockRain() {
        super("BlockRain", "shoots blocks into the air.", "25", "DIRT", "1.0");
        setUsage("/troll execute BlockRain {player} {blockCount} {blockType} {power}");
        setIcon(Material.ORANGE_WOOL);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {
        }
        BlockData b = Material.getMaterial(getArg(1).toUpperCase()).createBlockData();
        try {
            b = Material.getMaterial(args[2].toUpperCase()).createBlockData();
        } catch (Exception ignored) {}

        double c = Double.parseDouble(getArg(2));
        try {
            c = Double.parseDouble(args[3]);
        } catch (Exception ignored) {
        }
        for (int k = 0; k < a; k++) {
            spawnFallingBlock(b,p,c);
        }
    }

    public void spawnFallingBlock(BlockData bd, Player p, double power) {
        FallingBlock fallingBlock = p.getWorld().spawnFallingBlock(p.getLocation().add(0.5, 1, 0.5), bd);
        Random random = new Random();
        double x = random.nextDouble() - 0.5;
        double y = random.nextDouble() * 0.5;
        double z = random.nextDouble() - 0.5;
        Vector direction = new Vector(x, y, z).normalize();
        fallingBlock.setVelocity(direction.multiply(power));
    }
}
