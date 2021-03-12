package me.pljr.itemcommands.managers;

import lombok.AllArgsConstructor;
import me.pljr.itemcommands.config.Items;
import me.pljr.itemcommands.objects.CommandItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@AllArgsConstructor
public class CommandItemManager {

    private final Items items;

    public boolean check(Player player, ItemStack item){
        for (Map.Entry<String, CommandItem> entry : items.getItems().entrySet()){
            if (item.isSimilar(entry.getValue().getItem())){
                use(player, entry.getValue());
                return true;
            }
        }
        return false;
    }

    private void use(Player player, CommandItem commandItem){
        String playerName = player.getName();
        if (commandItem.isRemove()){
            player.getInventory().remove(commandItem.getItem());
            player.updateInventory();
        }
        for (String consoleCommand : commandItem.getConsoleCommands()){
            if (consoleCommand.equals("")) continue;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consoleCommand.replace("{player}", playerName));
        }
        for (String playerCommands : commandItem.getPlayerCommands()){
            if (playerCommands.equals("")) continue;
            Bukkit.dispatchCommand(player, playerCommands.replace("{player}", playerName));
        }
    }
}
