package com.shepherdjerred.riotbase.commands;

import com.shepherdjerred.riotbase.messages.AbstractParser;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to handle all the top level commands, which allows
 * nodes to easily get objects they need through this register.
 */
public class NodeRegister {

    private final AbstractParser parser;
    private final Map<String, CommandNode> commands;

    public NodeRegister(AbstractParser parser) {
        this.parser = parser;
        commands = new HashMap<>();
    }

    public void addNode(CommandNode node) {
        commands.put(node.getName(), node);
    }

    /**
     * Add the register's commands to Bukkit's Command Map
     *
     * @param javaPlugin
     */
    public void registerCommandsForBukkit(JavaPlugin javaPlugin) {
        String pluginName = javaPlugin.getName();
        try {
            final Field bukkitCommandMap = javaPlugin.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(javaPlugin.getServer());

            commands.values().forEach(command -> commandMap.register(pluginName, command));

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public AbstractParser getParser() {
        return parser;
    }

}
