package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutHeldItemSlot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.kubag5.g5troll.G5Troll;

import static pl.kubag5.g5troll.Reflections.*;

public class RollingHeldSlot extends Troll {
    public RollingHeldSlot() {
        super("RollingHeldSlot", "Rolling Held Slot", "10", "10");
        setIcon(Material.HOPPER);
        setUsage("/troll execute RollingHeldSlot {player} {seconds} {GTech1-speed}");
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        String[] args = event.getArgs();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        int b = Integer.parseInt(getArg(1));
        try {
            b = Integer.parseInt(args[2]);
        } catch (Exception ignored) {}
        int finalA = a;
        int finalB = b;
        BukkitRunnable task = new BukkitRunnable() {
            int c = finalA*utils.calcIntGTech1SpeedB(finalB);
            int d = 0;
            @Override
            public void run() {
                if (c < 0) {
                    Bukkit.getServer().getScheduler().cancelTask(getTaskId());
                } else {
                    c--;
                }
                if (p != null) {
                    try {
                        Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
                        PacketPlayOutHeldItemSlot p1 = new PacketPlayOutHeldItemSlot(d);
                        Object playerConnection = playerConnectionField.get(entityPlayer);
                        Object networkManager = networkManagerField.get(playerConnection);
                        sendPacket.invoke(networkManager, p1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    d++;
                    if (d == 9) d = 0;
                }
            }
        };
        task.runTaskTimer(G5Troll.getInstance(), utils.calcIntGTech1SpeedA(finalB), utils.calcIntGTech1SpeedA(finalB));
    }
}
