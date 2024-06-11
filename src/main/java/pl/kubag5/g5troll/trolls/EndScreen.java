package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

public class EndScreen extends Troll {
    public EndScreen() {
        super("EndScreen", "Shows the player a end screen.");
        setIcon(Material.DRAGON_EGG);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            ClientboundGameEventPacket p1 = new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 1);
            Reflections.sendPacket(p, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
