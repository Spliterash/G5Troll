package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

import java.lang.reflect.InvocationTargetException;

import static pl.kubag5.g5troll.Reflections.*;

public class LoadingScreen extends Troll{
    public LoadingScreen() {
        super("LoadingScreen", "Shows the player a loading screen." , "10");
        setIcon(Material.CHEST);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        String[] args = event.getArgs();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        try {
            // hp, food, sat
            PacketPlayOutGameStateChange p1 = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.e, 0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
              if (p != null) {
                  if (p.isOnline()) {
                      p.closeInventory();
                  }
              }
            }, a * 20L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
