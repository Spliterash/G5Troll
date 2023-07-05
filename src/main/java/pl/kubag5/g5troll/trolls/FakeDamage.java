package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static pl.kubag5.g5troll.Reflections.*;

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
            PacketPlayOutUpdateHealth p1 = new PacketPlayOutUpdateHealth(1,p.getFoodLevel(),0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
