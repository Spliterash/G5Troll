package pl.kubag5.g5troll;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

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

}
