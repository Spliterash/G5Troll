package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static pl.kubag5.g5troll.Reflections.*;

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
            PacketPlayOutUpdateHealth p1 = new PacketPlayOutUpdateHealth((float) p.getHealth(),0,0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
