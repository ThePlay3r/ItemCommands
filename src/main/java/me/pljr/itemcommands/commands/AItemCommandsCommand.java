package me.pljr.itemcommands.commands;

import me.pljr.itemcommands.config.Items;
import me.pljr.itemcommands.config.Lang;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AItemCommandsCommand extends BukkitCommand {

    private final Items items;

    public AItemCommandsCommand(Items items){
        super("aitemcommands", "itemcommands.admin.use");
        this.items = items;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /aitemcommands help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "itemcommands.admin.help")) return;
                sendMessage(player, Lang.ADMIN_HELP.get());
                return;
            }

            // /aitemcommands list
            if (args[0].equalsIgnoreCase("list")){
                if (!checkPerm(player, "itemcommands.admin.list")) return;
                sendMessage(player, Lang.LIST_TITLE.get());
                for (String itemName : items.getNames()){
                    sendMessage(player, Lang.LIST_FORMAT.get().replace("{name}", itemName));
                }
                return;
            }
        }

        else if (args.length == 2){
            // /aitecommands get <name>
            if (args[0].equalsIgnoreCase("get")){
                if (!checkPerm(player, "itemcommands.admin.get")) return;
                if (!items.getNames().contains(args[1])){
                    sendMessage(player, Lang.GET_FAILURE_NO_ITEM.get().replace("{item}", args[1]));
                    return;
                }
                ItemStack item = items.getItems().get(args[1]).getItem();
                if (player.getInventory().firstEmpty() == -1){
                    player.getWorld().dropItem(player.getLocation(), item);
                }else{
                    player.getInventory().addItem(item);
                    player.updateInventory();
                }
                sendMessage(player, Lang.GET_SUCCESS.get().replace("{item}", args[1]));
                return;
            }
        }

        if (checkPerm(player, "itemcommands.admin.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }
}
