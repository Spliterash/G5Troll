package pl.kubag5.g5troll.trolls;


import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.G5Troll;
import pl.kubag5.g5troll.Reflections;

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
        } catch (Exception ignored) {
        }
        try {
            ClientboundGameEventPacket p1 = new ClientboundGameEventPacket(ClientboundGameEventPacket.CHANGE_GAME_MODE, 1);
            ClientboundGameEventPacket p2 = new ClientboundGameEventPacket(ClientboundGameEventPacket.CHANGE_GAME_MODE, 0);
            Reflections.sendPacket(p, p1);
            p.setAllowFlight(false);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(G5Troll.getInstance(), () -> {
                if (!p.isOnline()) return;
                Reflections.sendPacket(p, p2);
            }, a * 20L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
