package com.bte;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFormatterPlugin extends JavaPlugin {

    private static final Pattern INPUT_PATTERN = Pattern.compile("^(\\d+),(\\d+),(\\d+)M$");

    @Override
    public void onEnable() {
        getLogger().info("BTE enabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("convert")) {
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /convert <number,number,numberM>");
            return true;
        }

        Matcher matcher = INPUT_PATTERN.matcher(args[0]);
        if (!matcher.matches()) {
            sender.sendMessage(ChatColor.RED + "Invalid format.");
            return true;
        }

        String result = matcher.group(1) + "," + matcher.group(2) + " " + matcher.group(3);
        sender.sendMessage(ChatColor.GREEN + "Output: " + ChatColor.WHITE + result);
        return true;
    }
}
