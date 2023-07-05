package pl.kubag5.g5troll.trolls;

import org.bukkit.Material;

public class SeeInventory extends Troll {
    public SeeInventory() {
        super("SeeInventory", "see player inventory.");
        setIcon(Material.STICK);

    }

    @Override
    public void execute(TrollEvent event) {
        event.getExecutor().openInventory(event.getTarget().getInventory());
    }
}
