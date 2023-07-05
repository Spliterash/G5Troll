package pl.kubag5.g5troll;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.Channel;
import java.util.Arrays;
public class Reflections {

    private static final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    private static final Version ver = calcVersion();

    public static final Class<?> craftPlayerClass = obc("entity.CraftPlayer");
    public static final Class<?> entityPlayerClass = nms("server.level.EntityPlayer");
    public static final Class<?> playerConnectionClass = nms("server.network.PlayerConnection");
    public static final Class<?> networkManagerClass = nms("network.NetworkManager");
    public static final Class<?> packetClass = nms("network.protocol.Packet");
    public static final Field playerConnectionField = getFieldSpecified(entityPlayerClass, playerConnectionClass);
    public static final Field networkManagerField = getFieldSpecified(playerConnectionClass, networkManagerClass);
    public static final Method entityPlayerHandleMethod = getMethodSpecified(craftPlayerClass, entityPlayerClass);

    public static final Method sendPacket = getMethodSpecified(networkManagerClass, void.class, packetClass);
    public static Class<?> nms(String name) {
        try {
            Class<?> clazz = null;
            if (ver == Version.NEW) {
                clazz = Class.forName(String.format("net.minecraft.%s", name));
            } else if (ver == Version.OLD) {
                String[] splitName = name.split("\\.");
                clazz = Class.forName(String.format("net.minecraft.server.%s.%s", serverVersion, splitName[splitName.length - 1]));
            }
            return clazz;
        } catch (ClassNotFoundException e) {
            if (!name.startsWith("network") && name.startsWith("Packet")) {
                return nms("network.protocol.game." + name);
            }
            throw new RuntimeException(e);
        }
    }

    public static Class<?> obc(String name) {
        try {
            return Class.forName(String.format("org.bukkit.craftbukkit.%s.%s", serverVersion, name));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Method getMethodSpecified(Class<?> clazz, Class<?> returnType, Class<?>... parameters) {
        method:
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() == parameters.length && method.getReturnType() == returnType) {
                Class<?>[] params = method.getParameterTypes();
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != parameters[i]) {
                        continue method;
                    }
                }
                method.setAccessible(true);
                return method;
            }
        }
        throw new RuntimeException("Could not find method with return type of (" + returnType + ") and parameters (" + Arrays.toString(parameters) + ")");
    }

    public static Field getFieldSpecified(Class<?> clazz, Class<?> type) {
        return getFieldSpecified(clazz, type, 0);
    }

    public static Field getFieldSpecified(Class<?> clazz, Class<?> type, int at) {
        at = Math.max(at, 0);
        int i = 0;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType() == type) {
                if (at == i++) {
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        throw new RuntimeException("Could not find field of " + clazz.getName() + " with type of " + type.getName() + " at " + at);
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