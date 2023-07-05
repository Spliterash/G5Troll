package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;
import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static pl.kubag5.g5troll.Reflections.*;

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
            PacketPlayOutGameStateChange p1 = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.e, 1);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
