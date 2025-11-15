package fr.ladder.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.properties.Property;
import fr.ladder.reflex.PluginInspector;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Snowtyy
 */
public class LadderSkinUtils implements SkinUtils.Implementation {

    private final Map<String, Property> _cache;

    public LadderSkinUtils() {
        _cache = new HashMap<>();
    }

    @Override
    public void loadAllSkins(JavaPlugin plugin, PluginInspector inspector) {
        final Pattern pattern = Pattern.compile("skin/.*\\.json");

        // fetch all skin files
        inspector.getResources(pattern).forEach(resource -> this.load(plugin, resource));
    }

    private void load(JavaPlugin plugin, String path) {
        try(InputStream inputStream = plugin.getResource(path)) {
            if(inputStream == null) {
                plugin.getLogger().warning("| File '" + path + "' wasn't found.");
                return;
            }

            try(JsonReader reader = new JsonReader(new InputStreamReader(inputStream))) {
                JsonElement json = new JsonParser().parse(reader);
                if(json instanceof JsonObject jsonObj) {
                    String value = jsonObj.get("value").getAsString();
                    String signature = jsonObj.has("signature") ? jsonObj.get("signature").getAsString() : null;

                    _cache.put(path, new Property("textures", value, signature));
                }
            }
            catch(JsonSyntaxException e) {
                plugin.getLogger().warning("| File '" + path + "' has a bad json syntax.");
            }
        } catch(IOException e) {
            plugin.getLogger().warning("An error occurred while loading lang file: " + path);
        }
    }

    @Override
    public Property get(String path, boolean copy) {
        String fullPath = "skin/" + path + ".json";
        var property = _cache.get(fullPath);


        return copy
                ? (property == null ? null : new Property("textures", property.getValue(), property.getSignature()))
                : property;
    }
}
