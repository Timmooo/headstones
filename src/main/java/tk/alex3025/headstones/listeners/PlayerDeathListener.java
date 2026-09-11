package tk.alex3025.headstones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import tk.alex3025.headstones.utils.ExperienceManager;
import tk.alex3025.headstones.utils.Headstone;

public class PlayerDeathListener extends ListenerBase {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        Player player = event.getPlayer();


        // Skip if WildLoaders' ChunkLoaderNPC (without hard dependency)
        if (Bukkit.getPluginManager().isPluginEnabled("WildLoaders")) {
            try {
                Class<?> chunkLoaderClazz = Class.forName("com.bgsoftware.wildloaders.api.npc.ChunkLoaderNPC");
                if (chunkLoaderClazz.isInstance(player)) {
                    return;
                }
            } catch (ClassNotFoundException ignored) {
            // WildLoaders not on classpath after all; proceed normally
            }
        }

        boolean keepExperience = !event.getKeepLevel() && player.hasPermission("headstones.keep-experience");
        boolean keepInventory = !event.getKeepInventory() && player.hasPermission("headstones.keep-inventory");

        if (!(keepExperience && keepInventory) || !player.getInventory().isEmpty() || ExperienceManager.getExperience(player) != 0)
            new Headstone(player).onPlayerDeath(event, keepExperience, keepInventory);
    }

}
