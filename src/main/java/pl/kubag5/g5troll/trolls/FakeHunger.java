package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

public class FakeHunger extends Troll {
    public FakeHunger() {
        super("FakeHunger", "Falsely sets the player's food level to 0.");
        setIcon(Material.ROTTEN_FLESH);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            ClientboundSetExperiencePacket p1 = new ClientboundSetExperiencePacket((float) p.getHealth(), 0, 0);
            Reflections.sendPacket(p, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
