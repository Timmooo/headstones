package tk.alex3025.headstones.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterListener extends ListenerBase {

    public WaterListener() {
        super(); // This calls the ListenerBase constructor to auto-register
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Block toBlock = event.getToBlock();

        // Prevent water or lava from flowing into player headstones
        if (toBlock.getType() == Material.PLAYER_HEAD || toBlock.getType() == Material.PLAYER_WALL_HEAD) {
            event.setCancelled(true);
        }
    }
}
