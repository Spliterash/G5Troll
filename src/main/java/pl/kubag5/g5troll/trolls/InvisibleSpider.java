package pl.kubag5.g5troll.trolls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;

public class InvisibleSpider extends Troll {
    public InvisibleSpider() {
        super("InvisibleSpider", "Spawn invisible spider", "1");
        setUsage("/troll execute InvisibleSpider {player} {count}");
        setIcon(Material.SPIDER_EYE);
    }

    @Override
    public void execute(TrollEvent event) {
        String[] args = event.getArgs();
        Player p = event.getTarget();
        int a = Integer.parseInt(getArg(0));
        try {
            a = Integer.parseInt(args[1]);
        } catch (Exception ignored) {}
        for (int i = 0; i < a; i++) {
            Spider spider = (Spider) p.getWorld().spawnEntity(p.getLocation(), EntityType.SPIDER);
            spider.setInvisible(true);
            spider.setTarget(p);
        }
    }
}
