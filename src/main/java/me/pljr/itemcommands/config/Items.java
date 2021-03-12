package me.pljr.itemcommands.config;

import lombok.Getter;
import me.pljr.itemcommands.objects.CommandItem;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Items {
    private final HashMap<String, CommandItem> items;
    private final List<Material> materials;
    private final List<String> names;

    public Items(ConfigManager config){
        items = new HashMap<>();
        materials = new ArrayList<>();
        names = new ArrayList<>();
        ConfigurationSection itemsCs = config.getConfigurationSection("items");
        for (String item : itemsCs.getKeys(false)){
            ItemStack itemStack = config.getSimpleItemStack("items."+item);
            items.put(item, new CommandItem(
                    config.getBoolean("items."+item+".remove"),
                    itemStack,
                    config.getStringList("items."+item+".console-commands"),
                    config.getStringList("items."+item+".player-commands")));
            if (!materials.contains(itemStack.getType())){
                materials.add(itemStack.getType());
            }
            if (!names.contains(item)){
                names.add(item);
            }
        }
    }
}
