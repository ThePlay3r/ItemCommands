package me.pljr.itemcommands;

import me.pljr.itemcommands.commands.AItemCommandsCommand;
import me.pljr.itemcommands.config.CfgItems;
import me.pljr.itemcommands.config.Lang;
import me.pljr.itemcommands.listeners.PlayerInteractListener;
import me.pljr.itemcommands.managers.CommandItemManager;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemCommands extends JavaPlugin {
    private static ConfigManager configManager;
    private static CommandItemManager commandItemManager;

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
        configManager = new ConfigManager(this, "config.yml");
        Lang.load(configManager);
        CfgItems.load(configManager);
    }

    private void setupManagers(){
        commandItemManager = new CommandItemManager();
    }

    private void setupCommands(){
        getCommand("aitemcommands").setExecutor(new AItemCommandsCommand());
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    public static CommandItemManager getCommandItemManager() {
        return commandItemManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
