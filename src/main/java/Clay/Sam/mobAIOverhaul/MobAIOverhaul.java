package Clay.Sam.mobAIOverhaul;

import Clay.Sam.mobAIOverhaul.events.TriggerAIPlayerMove;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobAIOverhaul extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;
        // Plugin startup logic

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new TriggerAIPlayerMove(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
