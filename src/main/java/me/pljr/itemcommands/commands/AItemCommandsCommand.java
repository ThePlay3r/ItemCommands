package me.pljr.itemcommands.commands;

import me.pljr.itemcommands.config.CfgItems;
import me.pljr.itemcommands.config.CfgLang;
import me.pljr.itemcommands.enums.Lang;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AItemCommandsCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "itemcommands.admin.use")) return false;
        if (args.length == 1){
            // /aitemcommands help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "itemcommands.admin.help")) return false;
                sendHelp(player, CfgLang.adminHelp);
                return true;
            }

            // /aitemcommands list
            if (args[0].equalsIgnoreCase("list")){
                if (!checkPerm(player, "itemcommands.admin.list")) return false;
                sendMessage(player, CfgLang.lang.get(Lang.LIST_TITLE));
                for (String itemName : CfgItems.names){
                    sendMessage(player, CfgLang.lang.get(Lang.LIST_FORMAT).replace("%name", itemName));
                }
                return true;
            }
        }

        else if (args.length == 2){
            // /aitecommands get <name>
            if (args[0].equalsIgnoreCase("get")){
                if (!checkPerm(player, "itemcommands.admin.get")) return false;
                if (!CfgItems.names.contains(args[1])){
                    sendMessage(player, CfgLang.lang.get(Lang.GET_FAILURE_NO_ITEM).replace("%item", args[1]));
                    return false;
                }
                ItemStack item = CfgItems.items.get(args[1]).getItem();
                if (player.getInventory().firstEmpty() == -1){
                    player.getWorld().dropItem(player.getLocation(), item);
                }else{
                    player.getInventory().addItem(item);
                    player.updateInventory();
                }
                sendMessage(player, CfgLang.lang.get(Lang.GET_SUCCESS).replace("%item", args[1]));
                return true;
            }
        }

        if (checkPerm(player, "itemcommands.admin.help")){
            sendHelp(player, CfgLang.adminHelp);
        }
        return false;
    }
}
