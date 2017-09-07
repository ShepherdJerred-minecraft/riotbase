package com.shepherdjerred.riotbase.permissions;

import org.bukkit.entity.Player;

public interface Permission {

    public void givePermission(Player player, String permission);

}