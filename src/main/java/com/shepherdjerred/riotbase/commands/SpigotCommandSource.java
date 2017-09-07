package com.shepherdjerred.riotbase.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotCommandSource implements CommandSource {

    private final CommandSender sender;

    public SpigotCommandSource(CommandSender sender) {
        this.sender = sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player getPlayer() {
        return (Player) sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getName() {
        return sender.getName();
    }

    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

}
