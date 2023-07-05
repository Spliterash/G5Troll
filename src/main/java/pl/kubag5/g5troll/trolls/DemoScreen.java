package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static pl.kubag5.g5troll.Reflections.*;

public class DemoScreen extends Troll{
    public DemoScreen() {
        super("DemoScreen", "Shows the player a demo screen.");
        setIcon(Material.SUNFLOWER);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            PacketPlayOutGameStateChange p1 = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
