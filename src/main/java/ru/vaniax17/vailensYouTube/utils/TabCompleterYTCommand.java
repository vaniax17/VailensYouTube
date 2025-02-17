package ru.vaniax17.vailensYouTube.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterYTCommand implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            if (player.hasPermission("VailensYouTube.add") && player.hasPermission("VailensYouTube.remove")) {
                return List.of(
                        "add",
                        "remove"

                );
            } else if (player.hasPermission("VailensYouTube.add") && !player.hasPermission("VailensYouTube.remove")) {
                return List.of(
                        "add"
                );
            } else if (player.hasPermission("VailensYouTube.remove") && !player.hasPermission("VailensYouTube.add")) {
                return List.of(
                        "remove"

                );
            } else {
                return List.of(
                        "yt"

                );
            }

        }
        if (args.length == 2) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .collect(Collectors.toList());
        }


        return List.of();
    }
}
