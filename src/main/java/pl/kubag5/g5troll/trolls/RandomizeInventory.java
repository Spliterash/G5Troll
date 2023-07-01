package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RandomizeInventory extends Troll {
    public RandomizeInventory() {
        super("RandomizeInventory", "Randomize items in inventory", null);
        setIcon(Material.MELON_SEEDS);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        shuffleInventory(p);
    }

    private void shuffleInventory(Player player) {
        ItemStack[] contents = player.getInventory().getContents();
        int size = contents.length;

        for (int i = 0; i < size; i++) {
            int randomSlot = (int) (Math.random() * size);

            ItemStack currentItem = contents[i];
            ItemStack randomItem = contents[randomSlot];

            contents[i] = randomItem;
            contents[randomSlot] = currentItem;
        }

        player.getInventory().setContents(contents);
        player.updateInventory();
    }
}
