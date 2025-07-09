package Clay.Sam.mobAIOverhaul;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.EnumSet;

public class SheepFlee implements Goal<Sheep> {

    public static final GoalKey<Sheep> key = GoalKey.of(Sheep.class, NamespacedKey.minecraft("flee"));

    Player runAwayFromPlayer;
    Sheep sheep;

    /**
     * Constructor for the SheepFlee goal.
     *
     * @param player The player that the sheep will flee from.
     * @param sheep  The sheep that will flee.
     */
    public SheepFlee(Player player, Sheep sheep) {
        this.runAwayFromPlayer = player;
        this.sheep = sheep;
    }

    @Override
    public boolean shouldActivate() {
        return false;
    }

    @Override
    public void start() {
        runAwayFromPlayer.sendMessage( "Sheep is fleeing from you!");
    }

    @Override
    public void tick() {
        //TODO: Implement logic to make the sheep flee from the player
        sheep.getPathfinder().moveTo(runAwayFromPlayer.getLocation(), 1);
    }

    @Override
    public GoalKey<Sheep> getKey() {
        return key;
    }

    @Override
    public EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE, GoalType.LOOK);
    }
}
