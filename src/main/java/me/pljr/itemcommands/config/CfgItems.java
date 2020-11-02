package me.pljr.itemcommands.config;

import me.pljr.itemcommands.objects.CommandItem;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgItems {
    public static HashMap<String, CommandItem> items;
    public static List<Material> materials;
    public static List<String> names;

    public static void load(ConfigManager config){
        CfgItems.items = new HashMap<>();
        CfgItems.materials = new ArrayList<>();
        CfgItems.names = new ArrayList<>();
        ConfigurationSection items = config.getConfigurationSection("items");
        for (String item : items.getKeys(false)){
            ItemStack itemStack = config.getSimpleItemStack("items."+item);
            CfgItems.items.put(item, new CommandItem(
                    config.getBoolean("items."+item+".remove"),
                    itemStack,
                    config.getStringList("items."+item+".console-commands"),
                    config.getStringList("items."+item+".player-commands")));
            if (!CfgItems.materials.contains(itemStack.getType())){
                CfgItems.materials.add(itemStack.getType());
            }
            if (!CfgItems.names.contains(item)){
                CfgItems.names.add(item);
            }
        }
    }
}
