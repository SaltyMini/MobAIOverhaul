package Clay.Sam.mobAIOverhaul;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class SheepFlee implements Goal<@NotNull Sheep> {

    public static final GoalKey<@NotNull Sheep> key = GoalKey.of(Sheep.class, NamespacedKey.minecraft("flee"));

    Player player;
    Sheep sheep;

    /**
     * Constructor for the SheepFlee goal.
     *
     * @param player The player that the sheep will flee from.
     * @param sheep  The sheep that will flee.
     */

    public SheepFlee(Player player, Sheep sheep) {
        this.player = player;
        this.sheep = sheep;
    }

    @Override
    public boolean shouldActivate() {
        // Only activate if player is within 32 blocks
        double distance = sheep.getLocation().distanceSquared(player.getLocation());
        return distance <= 40 * 40;
    }


    @Override
    public void start() {
        player.sendMessage( "Sheep is fleeing from you!");
    }

    @Override
    public void stop() {
        player.sendMessage("Sheep has stopped fleeing.");
    }


    @Override
    public void tick() {
        Location playerLoc = player.getLocation();
        Location sheepLoc = sheep.getLocation();

        // Check if already far enough away
        double currentDistance = sheepLoc.distanceSquared(playerLoc);
        if (currentDistance >= 40 * 40) {
            return; // Don't move if already safe
        }

        // Calculate direction away from player
        Vector direction = sheepLoc.toVector().subtract(playerLoc.toVector());
        if (direction.length() > 0) {
            direction.normalize();
        } else {
            // If exactly at same location, pick random direction
            direction = new Vector(Math.random() - 0.5, 0, Math.random() - 0.5).normalize();
        }

        // Move to a location 6 blocks away
        Location fleeLocation = sheepLoc.clone().add(direction.multiply(6));
        sheep.getPathfinder().moveTo(fleeLocation, 1.6);
    }

    @Override
    public @NotNull GoalKey<@NotNull Sheep> getKey() {
        return key;
    }

    @Override
    public @NotNull EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE, GoalType.LOOK);
    }

    @Override
    public boolean shouldStayActive() {
        Location playerLoc = player.getLocation();
        Location sheepLoc = sheep.getLocation();
        // Only move if player is still too close
        double currentDistance = sheepLoc.distanceSquared(playerLoc);

        if (currentDistance >= 40 * 40) { // Stop when 40+ blocks away
            return false;
        }
        return true;
    }
}