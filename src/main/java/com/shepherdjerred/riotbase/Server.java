package com.shepherdjerred.riotbase;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface Server {

    public Collection<? extends Player> getOnlinePlayers();
    public World getWorld(String worldName);
    public World getWorld(UUID worldUuid);
    public Player getPlayer(String playerName);
    public Player getPlayer(UUID playerUuid);

}
