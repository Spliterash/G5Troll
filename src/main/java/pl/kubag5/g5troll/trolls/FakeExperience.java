package pl.kubag5.g5troll.trolls;

import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.kubag5.g5troll.Reflections;

public class FakeExperience extends Troll {
    public FakeExperience() {
        super("FakeExperience", "Falsely sets the player's experience.", "69");
        setUsage("/troll execute FakeExperience {player} {level}");
        setIcon(Material.EXPERIENCE_BOTTLE);
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
            ClientboundSetExperiencePacket p1 = new ClientboundSetExperiencePacket(p.getExp(), p.getTotalExperience(), a);
            Reflections.sendPacket(p, p1);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
