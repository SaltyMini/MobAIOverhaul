package Clay.Sam.mobAIOverhaul;

import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.Plugin;

public class MobTrigger {

    private final Server server;
    private final Plugin plugin;

    public MobTrigger() {
        plugin = MobAIOverhaul.getPlugin();
        server = plugin.getServer();
    }

    public void playerInHearRangeMove(Player player, Entity entity) {

        if(player.isSneaking()) {
            return; // If the player is sneaking, do not trigger any AI behavior
        }

        switch(entity.getType()) {
            case SHEEP:
                Sheep sheep = (Sheep) entity;

                SheepFlee sheepFlee = new SheepFlee(player, sheep);

                if(server.getMobGoals().hasGoal(sheep, SheepFlee.key)) {
                    return;
                }
                server.getMobGoals().addGoal(sheep, 0, sheepFlee); // 0 is the priority, lower numbers are higher priority
                break;
        }

    }

    public void playerInVisionRangeMove(Player player) {
        // Logic for when a player is in the vision range of a mob
        // This could trigger specific AI behaviors or actions
    }

}
