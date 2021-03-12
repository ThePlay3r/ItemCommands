package me.pljr.itemcommands.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    ADMIN_HELP("" +
            "\n§a§lItemCommands Admin-Help" +
            "\n" +
            "\n§e/aitemcommands help §8» §fDisplays this message." +
            "\n§e/aitemcommands list §8» §fDisplays list of all custom items." +
            "\n§e/aitemcommands get <name> §8» §fGives you desired command item."),

    GET_SUCCESS("§aItemCommands §8» §b{item} §fhas been added to your inventory."),
    GET_FAILURE_NO_ITEM("§aItemCommands §8» §b{item} §fis not a command item."),
    LIST_TITLE("§aItemCommands §8» §bList of all items:"),
    LIST_FORMAT("§7- §f{name}");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        lang = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (Lang lang : Lang.values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.defaultValue);
            }else{
                Lang.lang.put(lang, config.getString(lang.toString()));
            }
        }
        config.save();
    }

    public String get(){
        return lang.getOrDefault(this, defaultValue);
    }
}
