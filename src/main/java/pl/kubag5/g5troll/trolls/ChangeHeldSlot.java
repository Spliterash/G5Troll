package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.PacketPlayOutHeldItemSlot;
import net.minecraft.network.protocol.game.PacketPlayOutWindowItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static pl.kubag5.g5troll.Reflections.*;

public class ChangeHeldSlot extends Troll {
    public ChangeHeldSlot() {
        super("ChangeHeldSlot", "Change held item slot.", "0");
        setUsage("/troll execute ChangeHeldSlot {player} {slot}");
        setIcon(Material.GOLDEN_AXE);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        String[] args = event.getArgs();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        if (a > 8 || a < 0) a = 0;
        try {
            Object entityPlayer = entityPlayerHandleMethod.invoke(craftPlayerClass.cast(p));
            PacketPlayOutHeldItemSlot p1 = new PacketPlayOutHeldItemSlot(a);
            Object playerConnection = playerConnectionField.get(entityPlayer);
            Object networkManager = networkManagerField.get(playerConnection);
            sendPacket.invoke(networkManager, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
