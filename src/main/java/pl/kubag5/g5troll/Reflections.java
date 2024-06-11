package pl.kubag5.g5troll;

import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflections {

    private static final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    private static final Version ver = calcVersion();

    public static void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    private static Version calcVersion() {
        int intVer = Integer.parseInt(serverVersion.split("_")[1]);
        if (intVer >= 17) {
            return Version.NEW;
        } else if (intVer >= 13) {
            return Version.OLD;
        } else return Version.UNKNOWN;
    }

    private enum Version {
        NEW,
        OLD,
        UNKNOWN
    }
}