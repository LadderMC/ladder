package fr.ladder.core;

import fr.ladder.api.LadderPlugin;
import fr.ladder.api.Task;
import fr.ladder.api.util.ItemUtils;
import fr.ladder.api.util.LadderItemUtils;
import fr.ladder.api.util.LadderSkinUtils;
import fr.ladder.api.util.SkinUtils;
import fr.ladder.polyglot.Messages;
import fr.ladder.polyglot.base.PolyglotMessages;
import fr.ladder.wirer.ServiceCollection;
import fr.ladder.wirer.base.WirerInjector;
import org.github.paperspigot.PaperSpigotConfig;

public class LadderEngine extends LadderPlugin {

    private final LadderExecutor _executor;

    private final WirerInjector _injector;

    public LadderEngine() {
        _executor = new LadderExecutor();
        // ============ SETUP INJECTOR ============
        _injector = new WirerInjector(this.getServer().getPluginManager());
        // ============ SETUP MESSAGES ============
        Messages.setImplementation(new PolyglotMessages(this));
        // ============ SETUP TASK ============
        Task.setImplementation(new LadderTask(this, _executor));
        // ============ SETUP UTILS ===============
        SkinUtils.setImplementation(new LadderSkinUtils());
        ItemUtils.setImplementation(new LadderItemUtils());
    }

    @Override
    public void setup(ServiceCollection services) {
        services.addScoped(_executor);
    }

    @Override
    public void onLoad() {
        _injector.injectAll();

        super.onLoad();
        PaperSpigotConfig.strengthEffectModifier = 0.0;
        PaperSpigotConfig.weaknessEffectModifier = 0.0;
    }

}