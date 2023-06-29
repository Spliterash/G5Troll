package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

import java.util.Random;

public class FakeBlocks extends Troll {
    public FakeBlocks() {
        super("FakeBlocks", "Creates random fake blocks around the player.",  new String[]{"25", "80"});
        setUsage("/troll execute FakeBlocks {player} {range} {count} ");
        setIcon(Material.BAMBOO);
    }

    BlockData[] fakeblocks = {
            Material.DIAMOND_ORE.createBlockData(),
            Material.WATER.createBlockData(),
            Material.BAMBOO.createBlockData(),
            Material.SAND.createBlockData(),
            Material.ANVIL.createBlockData(),
            Material.LAVA.createBlockData()

    };

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int b = Integer.parseInt(getArg(1));
        try {
            b = Integer.parseInt(args[2]);
        } catch (Exception ignored) {}
        replaceRandomBlocks(p,a,b);
    }

    private void replaceRandomBlocks(Player p, int radius, int count) {
        World world = p.getWorld();
        Random random = new Random();
        int startX = p.getLocation().getBlockX();
        int startY = p.getLocation().getBlockY();
        int startZ = p.getLocation().getBlockZ();

        for (int i = 0; i < count; i++) {
            int offsetX = random.nextInt(radius*2+1) - radius;
            int offsetY = random.nextInt(radius*2+1) - radius;
            int offsetZ = random.nextInt(radius*2+1) - radius;
            int newX = startX + offsetX;
            int newY = startY + offsetY;
            int newZ = startZ + offsetZ;
            p.sendBlockChange( new Location(world, newX, newY, newZ), getRandomBlockState());
        }

    }
    private BlockData getRandomBlockState() {
        Random random = new Random();
        int index = random.nextInt(fakeblocks.length);
        return fakeblocks[index];
    }
}
