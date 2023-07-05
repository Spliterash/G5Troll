package pl.kubag5.g5troll.trolls;


import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;

import java.lang.reflect.InvocationTargetException;

import static pl.kubag5.g5troll.Reflections.*;

public class FakeCreative extends Troll {
    public FakeCreative() {
        super("FakeCreative", "Fake creative for player.", "30");
        setIcon(Material.BRICKS);
        setUsage("/troll execute FakeCreative {player} {seconds}");
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        if (p.getGameMode().equals(GameMode.CREATIVE)) return;
        String[] args = event.getArgs();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        try {
            PacketPlayOutGameStateChange p1 = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.d, 1);
            PacketPlayOutGameStateChange p2 = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.d, 0);
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
            p.setAllowFlight(false);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                if (!p.isOnline()) return;
                try {
                    sendPacket.invoke(networkManager, p2);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }, a*20L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
