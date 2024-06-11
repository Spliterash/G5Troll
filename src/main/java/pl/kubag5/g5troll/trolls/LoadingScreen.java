package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;
import pl.kubag5.g5troll.Reflections;

public class LoadingScreen extends Troll {
    public LoadingScreen() {
        super("LoadingScreen", "Shows the player a loading screen.", "10");
        setUsage("/troll execute LoadingScreen {player} {seconds}");
        setIcon(Material.CHEST);
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
        try {
            // hp, food, sat
            ClientboundGameEventPacket p1 = new ClientboundGameEventPacket(ClientboundGameEventPacket.LEVEL_CHUNKS_LOAD_START, 0);
            Reflections.sendPacket(p, p1);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                if (p.isOnline()) {
                    p.closeInventory();
                }
            }, a * 20L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
