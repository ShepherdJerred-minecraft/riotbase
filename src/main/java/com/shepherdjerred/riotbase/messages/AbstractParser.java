package com.shepherdjerred.riotbase.messages;

import org.bukkit.ChatColor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractParser {

    private final ResourceBundle resourceBundle;

    public AbstractParser(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Translates chat codes into a colored string
     * @param input The string to be colored
     * @return A formatted, colored string
     */
    public String replaceColorCodes(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Pulls a message from the resource bundle, and colors it
     * @param prefix Whether or not the message should be prefixed
     * @param path The path to pull from
     * @param args Arguments format the string with
     * @return The colored string at the path
     */
    public String colorString(boolean prefix, String path, Object... args) {
        if (!resourceBundle.containsKey(path)) {
            return "Cannot find path " + path + " in " + resourceBundle.getBaseBundleName();
        }
        String result = "";
        if (prefix) {
            result = result.concat(colorString(false, "prefix"));
        }
        result = result.concat(replaceColorCodes(resourceBundle.getString(path)));
        if (args.length > 0) {
            result = MessageFormat.format(result, args);
        }
        return result;
    }

    /**
     * Pulls messages from the resource bundle, and colors them
     * @param path The path to pull from
     * @return The strings at the path, colorized
     */
    public List<String> colorBundleStringList(String path) {
        List<String> output = new ArrayList<>();
        if (!resourceBundle.containsKey(path)) {
            output.add("Cannot find path " + path + " in " + resourceBundle.getBaseBundleName());
        } else {
            for (String s : resourceBundle.getStringArray(path)) {
                output.add(replaceColorCodes(s));
            }
        }
        return output;
    }

}
