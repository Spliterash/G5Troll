package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Pumpkin extends Troll {
    public Pumpkin() {
        super("Pumpkin", "Put the pumpkin on the player", null);
        setIcon(Material.CARVED_PUMPKIN);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        ItemStack it = new ItemStack(Material.CARVED_PUMPKIN);
        if (p.getInventory().getHelmet() == null) {
            p.getInventory().setHelmet(it);
        } else {
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItemNaturally(p.getLocation(), p.getInventory().getHelmet());
            } else {
                p.getInventory().addItem(p.getInventory().getHelmet());
            }
            p.getInventory().setHelmet(it);
        }
    }
}
