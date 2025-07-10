package Clay.Sam.mobAIOverhaul.events;

import Clay.Sam.mobAIOverhaul.MobAIOverhaul;
import Clay.Sam.mobAIOverhaul.SheepFlee;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class PlayerAttackMob implements Listener {

    private final Plugin plugin;
    private final Server server;

    public PlayerAttackMob() {

        this.plugin = MobAIOverhaul.getPlugin();
        this.server = plugin.getServer();

    }

    @EventHandler
    public void onPlayerAttackMob(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player player) || !(event.getEntity() instanceof Sheep sheep)) {
            return;
        }

        // Check if sheep already has a flee goal to prevent duplicates
        if(server.getMobGoals().hasGoal(sheep, SheepFlee.key)) {
            return;
        }

        SheepFlee sheepFlee = new SheepFlee(player, sheep);
        server.getMobGoals().addGoal(sheep, 0, sheepFlee);
    }

}