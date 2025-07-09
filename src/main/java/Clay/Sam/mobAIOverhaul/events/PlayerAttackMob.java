package Clay.Sam.mobAIOverhaul.events;

import Clay.Sam.mobAIOverhaul.SheepFlee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttackMob implements Listener {

    @EventHandler
    public void onPlayerAttackMob(EntityDamageByEntityEvent event) {

        if(!(event.getDamager() instanceof Player)) {
            //attacker is not a player
            return;
        }

        SheepFlee sheepFlee = new SheepFlee((Player) event.getDamager(), (org.bukkit.entity.Sheep) event.getEntity());

        //Make sheep flee
        //Setting AI goal to flee

    }

}
