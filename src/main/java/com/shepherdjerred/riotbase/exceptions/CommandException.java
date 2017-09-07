package com.shepherdjerred.riotbase.exceptions;

public class CommandException extends Exception {

    private final String playerException;

    public CommandException(String exception, String playerException) {
        super(exception);
        this.playerException = playerException;
    }

    public String getPlayerException() {
        return playerException;
    }

}
