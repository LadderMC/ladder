package fr.ladder.api.util;

import com.mojang.authlib.properties.Property;
import fr.ladder.reflex.PluginInspector;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Snowtyy
 */
public final class SkinUtils {

    private static Implementation implementation;

    private SkinUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void setImplementation(Implementation implementation) {
        SkinUtils.implementation = implementation;
    }

    public static void loadAllSkins(JavaPlugin plugin, PluginInspector inspector) {
        implementation.loadAllSkins(plugin, inspector);
    }

    public static Property get(String path, boolean copy) {
        return implementation.get(path, copy);
    }

    public static Property get(String path) {
        return implementation.get(path, true);
    }

    public interface Implementation {

        void loadAllSkins(JavaPlugin plugin, PluginInspector inspector);

        Property get(String path, boolean copy);

    }
}
