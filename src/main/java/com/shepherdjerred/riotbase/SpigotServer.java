package com.shepherdjerred.riotbase;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class SpigotServer implements Server {

    public Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    public World getWorld(String worldName) {
        return Bukkit.getWorld(worldName);
    }

    public World getWorld(UUID worldUuid) {
        return Bukkit.getWorld(worldUuid);
    }

    public Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    public Player getPlayer(UUID playerUuid) {
        return Bukkit.getPlayer(playerUuid);
    }

    public org.bukkit.Server getServer() {
        return Bukkit.getServer();
    }
}
