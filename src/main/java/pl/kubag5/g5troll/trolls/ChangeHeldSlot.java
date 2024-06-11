package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

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
        } catch (Exception ignored) {
        }
        if (a > 8 || a < 0) a = 0;
        try {
            ClientboundSetCarriedItemPacket p1 = new ClientboundSetCarriedItemPacket(a);
            Reflections.sendPacket(p, p1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
