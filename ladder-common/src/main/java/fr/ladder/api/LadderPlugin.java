package fr.ladder.api;

import fr.ladder.api.util.SkinUtils;
import fr.ladder.polyglot.Messages;
import fr.ladder.reflex.PluginInspector;
import fr.ladder.reflex.Reflex;
import fr.ladder.wirer.InjectedPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author Snowtyy
 */
public abstract class LadderPlugin extends JavaPlugin implements InjectedPlugin {

    public LadderPlugin() {

    }

    @Override
    public void onLoad() {
        final Logger logger = Bukkit.getLogger();
        logger.info("==================[ loading: " + this.getDescription().getName() + " ]==================");
        super.saveDefaultConfig();

        try(PluginInspector inspector = Reflex.getInspector(this)) {
            SkinUtils.loadAllSkins(this, inspector);
        }
    }

    @Override
    public void onEnable() {
        final Logger logger = Bukkit.getLogger();
        logger.info("==================[ enabling: " + this.getDescription().getName() + " ]==================");
        Messages.loadAllMessages(this);
    }
}
