package me.pljr.itemcommands.listeners;

import lombok.AllArgsConstructor;
import me.pljr.itemcommands.ItemCommands;
import me.pljr.itemcommands.config.Items;
import me.pljr.itemcommands.managers.CommandItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class PlayerInteractListener implements Listener {

    private final Items items;
    private final CommandItemManager manager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;
        ItemStack itemStack = event.getItem();
        Material material = itemStack.getType();
        if (!items.getMaterials().contains(material)) return;
        Player player = event.getPlayer();
        manager.check(player, itemStack);
    }
}
