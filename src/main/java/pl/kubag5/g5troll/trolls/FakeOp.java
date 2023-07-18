package pl.kubag5.g5troll.trolls;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FakeOp extends Troll {
    public FakeOp() {
        super("FakeOp", "Sends op message.");
    }

    @Override
    public void execute(TrollEvent event) {
        Player p = event.getTarget();
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" +event.getTarget().getName()+ ": Made " + p.getName() + " a server operator]");
    }

}
