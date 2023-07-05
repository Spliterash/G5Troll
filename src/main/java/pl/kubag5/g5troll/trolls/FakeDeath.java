package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutUpdateHealth;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

import java.lang.reflect.InvocationTargetException;

import static pl.kubag5.g5troll.Reflections.*;

public class FakeDeath extends Troll{
    public FakeDeath() {
        super("FakeDeath", "Sends the death screen to the player.");
        setIcon(Material.SKELETON_SKULL);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        try {
            // hp, food, sat
            PacketPlayOutUpdateHealth p1 = new PacketPlayOutUpdateHealth(0,0,0);
            PacketPlayOutUpdateHealth p2 = new PacketPlayOutUpdateHealth((int) p.getHealth(),p.getFoodLevel(),0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                try {
                    sendPacket.invoke(networkManager, p2);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }, 3L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
