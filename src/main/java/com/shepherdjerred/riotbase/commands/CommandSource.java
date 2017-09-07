package com.shepherdjerred.riotbase.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface CommandSource {

    public boolean isPlayer();

    public Player getPlayer();

    public CommandSender getSender();

    public String getName();

    public void sendMessage(String message);

}
