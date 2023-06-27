package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DirtInventory extends Troll {
    public DirtInventory() {
        super("DirtInventory", "Drops all items and fills inventory with dirt.", null);
    }

    @Override
    public void execute(String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        ItemStack[] contents = p.getInventory().getContents();
        p.getInventory().clear();
        for (ItemStack it : contents) {
            if (it != null) {
                p.getWorld().dropItemNaturally(p.getLocation(), it);
            }
        }
        Inventory inventory = p.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.DIRT));
        }
        p.updateInventory();
    }
}
