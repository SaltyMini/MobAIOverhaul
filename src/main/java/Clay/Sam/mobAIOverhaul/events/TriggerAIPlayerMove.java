package Clay.Sam.mobAIOverhaul.events;

import Clay.Sam.mobAIOverhaul.MobTrigger;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.joml.Vector3d;

import java.util.List;

public class TriggerAIPlayerMove implements Listener {

    MobTrigger mobTrigger;

    private Vector3d hearingRange = new Vector3d(24, 20, 24);
    private Vector3d visionRange = new Vector3d(16, 10, 16);


    public TriggerAIPlayerMove() {
        // Constructor logic can be added here if needed
        mobTrigger = new MobTrigger();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        List<Entity> entityIsHearRange = event.getPlayer().getNearbyEntities(hearingRange.x, hearingRange.y, hearingRange.z);

        double movementDistance = event.getFrom().distanceSquared(event.getTo());
        if(movementDistance > 0.1) { // Lower threshold for more sensitive detection
            for(Entity entity : entityIsHearRange) {
                mobTrigger.playerInHearRangeMove(event.getPlayer(), entity);
            }
        }
    }


}
