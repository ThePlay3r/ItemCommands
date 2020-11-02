package me.pljr.itemcommands.config;

import me.pljr.itemcommands.enums.Lang;
import me.pljr.pljrapi.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public class CfgLang {
    public static List<String> adminHelp;
    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        CfgLang.adminHelp = config.getStringList("admin-help");
        CfgLang.lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }
}
