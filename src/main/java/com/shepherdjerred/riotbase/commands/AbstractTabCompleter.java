package com.shepherdjerred.riotbase.commands;

import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class
AbstractTabCompleter implements TabCompleter {

    private final String name;

    public AbstractTabCompleter(String name) {
        this.name = name;
    }

    public void register(JavaPlugin javaPlugin) {
        javaPlugin.getCommand(name).setTabCompleter(this);
    }

}
