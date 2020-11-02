package me.pljr.itemcommands.listeners;

import me.pljr.itemcommands.ItemCommands;
import me.pljr.itemcommands.config.CfgItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;
        ItemStack itemStack = event.getItem();
        Material material = itemStack.getType();
        if (!CfgItems.materials.contains(material)) return;
        Player player = event.getPlayer();
        ItemCommands.getCommandItemManager().check(player, itemStack);
    }
}
