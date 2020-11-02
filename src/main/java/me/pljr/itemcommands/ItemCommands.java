package me.pljr.itemcommands;

import me.pljr.itemcommands.commands.AItemCommandsCommand;
import me.pljr.itemcommands.config.CfgItems;
import me.pljr.itemcommands.config.CfgLang;
import me.pljr.itemcommands.listeners.PlayerInteractListener;
import me.pljr.itemcommands.managers.CommandItemManager;
import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemCommands extends JavaPlugin {
    private static ConfigManager configManager;
    private static CommandItemManager commandItemManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        setupConfig();
        setupManagers();
        setupCommands();
        setupListeners();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cItemCommands: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aItemCommands: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cItemCommands:", "config.yml");
        CfgLang.load(configManager);
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
