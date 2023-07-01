package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Message extends Troll {

    public Message() {
        super("Message", "Sending message as a player.", new String[]{"I am noob."});
        setUsage("/troll execute Message {player} [message]");
        setIcon(Material.OAK_BUTTON);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = Bukkit.getPlayer(args[0]);
        if (args.length != 1) {
            String str = "";
            for (int i = 1; i < args.length; i++) {
                str += args[i] + " ";
            }
            p.chat(str);
        } else {
            p.chat(getArg(0));
        }
    }
}
