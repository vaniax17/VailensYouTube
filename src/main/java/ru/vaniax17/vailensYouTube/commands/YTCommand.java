package ru.vaniax17.vailensYouTube.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.vaniax17.vailensYouTube.VailensYouTube;
import ru.vaniax17.vailensYouTube.utils.Config;
import ru.vaniax17.vailensYouTube.utils.CooldownManager;
import ru.vaniax17.vailensYouTube.utils.Lang;
import ru.vaniax17.vailensYouTube.utils.YouTuberCfg;

public class YTCommand implements CommandExecutor {
    private VailensYouTube plugin = VailensYouTube.getInst();
    private Lang lang = new Lang(plugin);
    private Config config = new Config(plugin);
    private CooldownManager cooldownManager = new CooldownManager(config.getCooldown());

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.getConsoleMsg());
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(lang.getMinArgs());
            return true;
        }
        if (args.length == 1) {
            String arg = args[0];
            if (!player.hasPermission("VailensYouTube.add") && arg.equalsIgnoreCase("add")) {
                player.sendMessage(lang.getPermAddToYouTubers());
                return true;
            } else if (arg.equalsIgnoreCase("remove") && !player.hasPermission("VailensYouTube.remove")) {
                player.sendMessage(lang.getPermRemoveToYouTubers());
                return true;
            }
        }
        if (args.length == 2 && !args[0].equalsIgnoreCase("yt") && !args[0].equalsIgnoreCase("remove") || args.length > 3) {
            player.sendMessage(lang.getMinArgs());
            return true;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
            String name = args[1].toLowerCase();
            String link = args[2];
            Player youtuber = Bukkit.getPlayer(name);
            if (youtuber == null) {
                player.sendMessage(lang.getPlayerMustBeOnline().replace("{youtuber}", name));
                return true;
            } else if (YouTuberCfg.containsYoutuber(youtuber)) {
                player.sendMessage(lang.getAlreadyInFile().replace("{youtuber}", name));
                return true;
            } else {
                YouTuberCfg.setYoutuberChannel(youtuber, link);
            }
            player.sendMessage(lang.getSuccessAddYouTuber().replace("{youtuber}", youtuber.getDisplayName()).replace("{link}", link));
            return true;
        } else if (args[0].equalsIgnoreCase("remove")) {
            String name = args[1].toLowerCase();
            if (!YouTuberCfg.containsYoutuberForRemove(name)) {
                player.sendMessage(lang.getYoutuberForRemoveUndefined().replace("{youtuber}", name));
            } else {
                YouTuberCfg.removeYoutuber(name);
                player.sendMessage(lang.getSuccessRemoveYouTuber().replace("{youtuber}", name));
            }
            return true;

        }
        if (!YouTuberCfg.containsYoutuber(player) && args.length == 1 && args[0].equalsIgnoreCase("yt")) {
            player.sendMessage(lang.getNotYouTubersFile());
            return true;
        } else if (cooldownManager.isOnCooldown(player, args[0])) {
            long remainingMillis = cooldownManager.getRemainingCooldown(player, args[0]);
            long totalSeconds = remainingMillis / 1000;
            long minutes = totalSeconds / 60;
            long seconds = totalSeconds % 60;
            String timeLeft;
            if (minutes > 0 && seconds > 0) {
                timeLeft = String.format("%dм %dс", minutes, seconds);
            } else if (minutes > 0) {
                timeLeft = String.format("%dм", minutes);
            } else {
                timeLeft = String.format("%dс", seconds);
            }
            player.sendMessage(lang.getMessageCooldown().replace("{left}", timeLeft));
            return true;
        }
        else if (YouTuberCfg.containsYoutuber(player) && args.length == 1 && args[0].equalsIgnoreCase("yt")) {
            String channel = YouTuberCfg.getYoutuberChannel(player);
            for (String ytMsg : lang.getMessageForYouTubers()) {
                Bukkit.broadcastMessage(ytMsg.replace("{youtuber}", player.getName()).replace("{link}", channel));
            }
            cooldownManager.setCooldown(player, args[0]);
            return true;
        }
        if (!args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("remove") && !args[0].equalsIgnoreCase("yt")) {
            player.sendMessage(lang.getMinArgs());
            return true;
        }




        return true;
    }
}
