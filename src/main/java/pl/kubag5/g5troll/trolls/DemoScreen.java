package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

public class DemoScreen extends Troll {
    public DemoScreen() {
        super("DemoScreen", "Shows the player a demo screen.");
        setIcon(Material.SUNFLOWER);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            ClientboundGameEventPacket p1 = new ClientboundGameEventPacket(ClientboundGameEventPacket.DEMO_EVENT, 0);
            Reflections.sendPacket(p, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
