package me.cosmic.prison.org.Listeners;

import me.cosmic.prison.org.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BreakEvent implements Listener {
    int coal;
    int iron;
    int a = coal+1;
    int b = iron+1;
    private Main plugin;

    public BreakEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.STONE)) {
            e.setCancelled(true);
        }
        Player p = e.getPlayer();
        if (e.getBlock().getType().equals(Material.COAL_ORE)) {
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            e.getBlock().getDrops().clear();
            p.getInventory().addItem(plugin.getUtils().block());
            p.updateInventory();
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.COAL_ORE);
                    plugin.getData().getConfig().set(p.getName(), a);
                    plugin.getData().saveConfig();
                    plugin.getData().reloadConfig();
                }
            }, 80L);
        }
        if (e.getBlock().getType().equals(Material.IRON_ORE)) {
            if (coal > 100) {
                e.setCancelled(true);
                e.getBlock().setType(Material.STONE);
                e.getBlock().getDrops().clear();
                p.getInventory().addItem(plugin.getUtils().ferro());
                p.updateInventory();
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        e.getBlock().setType(Material.COAL_ORE);
                        plugin.getData().getConfig().set(p.getName(), b);
                        plugin.getData().saveConfig();
                        plugin.getData().reloadConfig();
                    }
                }, 80L);
            }
        }if (coal < 100){
            p.sendMessage(plugin.getUtils().text(plugin.getConfig().getString("InsufficientToIron")));
            e.setCancelled(true);
        }
    }
}

