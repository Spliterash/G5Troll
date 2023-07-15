package pl.kubag5.g5troll.trolls;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FakeLava extends Troll {
    public FakeLava() {
        super("FakeLava", "creates false lava in player");
        setIcon(Material.LAVA_BUCKET);
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        utils.fill3x3Air(p.getLocation(), Material.LAVA, true, p);
    }
}
