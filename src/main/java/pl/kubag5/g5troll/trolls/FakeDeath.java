package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;
import pl.kubag5.g5troll.Reflections;

public class FakeDeath extends Troll {
    public FakeDeath() {
        super("FakeDeath", "Sends the death screen to the player.");
        setIcon(Material.SKELETON_SKULL);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            ClientboundSetHealthPacket p1 = new ClientboundSetHealthPacket(0, 0, 0);
            ClientboundSetHealthPacket p2 = new ClientboundSetHealthPacket((int) p.getHealth(), p.getFoodLevel(), 0);
            Reflections.sendPacket(p, p1);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                Reflections.sendPacket(p, p2);
            }, 3L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
