package com.shepherdjerred.riotbase.commands;

import java.util.ArrayList;
import java.util.List;

public class NodeInfo {

    private final String name;
    private final String permission;
    private final String description;
    private final String usage;
    private final int requiredArgs;
    private final boolean canConsoleUse;
    private final List<String> aliases;

    public NodeInfo(String name, String permission, String description, String usage, int requiredArgs, boolean canConsoleUse) {
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.usage = usage;
        this.requiredArgs = requiredArgs;
        this.canConsoleUse = canConsoleUse;
        this.aliases = new ArrayList<>();
    }

    public NodeInfo(String name, String permission, String description, String usage, int requiredArgs, boolean canConsoleUse, List<String> aliases) {
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.usage = usage;
        this.requiredArgs = requiredArgs;
        this.canConsoleUse = canConsoleUse;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public boolean canConsoleUse() {
        return canConsoleUse;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public int getRequiredArgs() {
        return requiredArgs;
    }

}
