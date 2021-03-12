package me.pljr.itemcommands;

import lombok.Getter;
import me.pljr.itemcommands.commands.AItemCommandsCommand;
import me.pljr.itemcommands.config.Items;
import me.pljr.itemcommands.config.Lang;
import me.pljr.itemcommands.listeners.PlayerInteractListener;
import me.pljr.itemcommands.managers.CommandItemManager;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemCommands extends JavaPlugin {

    private CommandItemManager commandItemManager;

    @Getter private Items items;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupConfig();
        setupManagers();
        setupCommands();
        setupListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        Lang.load(new ConfigManager(this, "lang.yml"));
        items = new Items(new ConfigManager(this, "config.yml"));
    }

    private void setupManagers(){
        commandItemManager = new CommandItemManager(items);
    }

    private void setupCommands(){
        getCommand("aitemcommands").setExecutor(new AItemCommandsCommand(items));
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(items, commandItemManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
