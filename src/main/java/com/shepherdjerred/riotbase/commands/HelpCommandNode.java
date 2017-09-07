package com.shepherdjerred.riotbase.commands;

import java.util.Arrays;

public class HelpCommandNode extends CommandNode {

    private final CommandNode parent;

    public HelpCommandNode(NodeRegister register, CommandNode parent) {
        super(register, new NodeInfo(
                "help",
                "",
                "Shows help for the " + parent.getNodeInfo().getName() + " command",
                "/" + parent.getNodeInfo().getName() + " help",
                0,
                true,
                Arrays.asList("?", "h")
        ));
        this.parent = parent;
    }

    @Override
    public void execute(SpigotCommandSource sender, String[] args) {
        // TODO Add support for subcommand arguments
        // Such as /command help <subcommand>

        NodeInfo parentCmd = parent.getNodeInfo();

        sender.sendMessage(parser.colorString(false, "generic.helpHeader", parentCmd.getUsage(), parentCmd.getDescription()));
        parent.getChildren().values().forEach(command -> {
            NodeInfo subCmd = command.getNodeInfo();
            sender.sendMessage(parser.colorString(false, "generic.helpSubcommand", subCmd.getUsage(), subCmd.getDescription()));
        });
    }
}
