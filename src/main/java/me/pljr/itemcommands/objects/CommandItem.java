package me.pljr.itemcommands.objects;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommandItem {
    private final boolean remove;
    private final ItemStack item;
    private final List<String> consoleCommands;
    private final List<String> playerCommands;

    public CommandItem(boolean remove, ItemStack item, List<String> consoleCommands, List<String> playerCommands){
        this.remove = remove;
        this.item = item;
        this.consoleCommands = consoleCommands;
        this.playerCommands = playerCommands;
    }

    public boolean isRemove() {
        return remove;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getConsoleCommands() {
        return consoleCommands;
    }

    public List<String> getPlayerCommands() {
        return playerCommands;
    }
}
