package me.pljr.itemcommands.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    NO_CONSOLE,
    GET_SUCCESS,
    GET_FAILURE_NO_ITEM,
    LIST_TITLE,
    LIST_FORMAT;
    public static List<String> ADMIN_HELP;

    private static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        ADMIN_HELP = config.getStringList("admin-help");
        lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            Lang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }

    public String get(){
        return lang.get(this);
    }
}
