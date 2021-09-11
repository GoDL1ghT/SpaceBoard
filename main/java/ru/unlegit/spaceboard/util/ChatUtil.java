package ru.unlegit.spaceboard.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.List;

@UtilityClass
public class ChatUtil {

    public static String color(String input) {
        return input == null ? "" : ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> color(@NonNull List<String> input) {
        input.replaceAll(s -> s.contains("&") ? color(s) : s);
        return input;
    }
}