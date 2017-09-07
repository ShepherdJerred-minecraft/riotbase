package com.shepherdjerred.riotbase.commands;

import com.shepherdjerred.riotbase.messages.AbstractParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class CommandNode extends BukkitCommand implements TabCompleter {

    protected final NodeInfo nodeInfo;
    protected final NodeRegister nodeRegister;
    protected final Map<String, CommandNode> children;

    protected final AbstractParser parser;

    public CommandNode(NodeRegister nodeRegister, NodeInfo nodeInfo, CommandNode... children) {
        super(nodeInfo.getName());
        this.nodeInfo = nodeInfo;
        this.nodeRegister = nodeRegister;
        this.parser = nodeRegister.getParser();
        this.children = new HashMap<>();

        // Register the given children
        for (CommandNode child : children) {
            this.children.put(child.getName(), child);
        }

        // Create an automatic help command
        if (!nodeInfo.getName().equals("help")) {
            this.children.put("help", new HelpCommandNode(nodeRegister, this));
        }
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!sender.hasPermission(nodeInfo.getPermission())) {
            sender.sendMessage(parser.colorString(true, "generic.noCommandPerms"));
            return true;
        }
        if (!(sender instanceof Player) && !nodeInfo.canConsoleUse()) {
            sender.sendMessage(parser.colorString(true, "generic.noConsole"));
            return true;
        }
        if (args.length > 0) {
            String subCommand = args[0];
            String[] argsWithoutSubCommand = Arrays.copyOfRange(args, 1, args.length);
            for (CommandNode child : children.values()) {
                if (subCommand.equalsIgnoreCase(child.getNodeInfo().getName())) {
                    child.execute(sender, s, argsWithoutSubCommand);
                    return true;
                }
                for (String alias : child.getNodeInfo().getAliases()) {
                    if (subCommand.equalsIgnoreCase(alias)) {
                        child.execute(sender, s, argsWithoutSubCommand);
                        return true;
                    }
                }
            }
        }
        if (args.length < nodeInfo.getRequiredArgs()) {
            sender.sendMessage(parser.colorString(true, "generic.incorrectArgCount", nodeInfo.getUsage()));
            return true;
        }
        execute(new SpigotCommandSource(sender), args);
        return true;
    }

    public abstract void execute(SpigotCommandSource sender, String[] args);

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public Map<String, CommandNode> getChildren() {
        return children;
    }

    public CommandNode getChild(String name) {
        if (children.values().stream().anyMatch(str -> str.getNodeInfo().getName().equalsIgnoreCase(name))) {
            return children.values().stream().filter(str -> str.getNodeInfo().getName().equalsIgnoreCase(name)).findFirst().get();
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
