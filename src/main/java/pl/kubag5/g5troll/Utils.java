package pl.kubag5.g5troll;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Utils {
    public void setRotation(Entity e, float yaw, float pitch) {
        try {
            e.setRotation(yaw, pitch);
        } catch (Exception ex) {
            Location loc = e.getLocation();
            loc.setYaw(yaw);
            loc.setPitch(pitch);
            e.teleport(loc);
        }
    }

    public String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }


    public int calcIntGTech1SpeedA(int speed) {
        if (speed >= 20) return 1;
        if (speed >= 10) return 2;
        if (speed >= 5) return 4;
        if (speed >= 4) return 5;
        if (speed >= 2) return 10;
        return 20;
    }

    public int calcIntGTech1SpeedB(int speed) {
        if (speed >= 20) return 20;
        if (speed >= 10) return 10;
        if (speed >= 5) return 5;
        if (speed >= 4) return 4;
        if (speed >= 2) return 2;
        return 1;
    }

}
