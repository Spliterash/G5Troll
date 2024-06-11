package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

public class FakeDamage extends Troll {
    public FakeDamage() {
        super("FakeDamage", "Fake beats the player to 1HP.");
        setIcon(Material.IRON_SWORD);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            ClientboundSetHealthPacket p1 = new ClientboundSetHealthPacket(1, p.getFoodLevel(), 0);
            Reflections.sendPacket(p, p1);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
