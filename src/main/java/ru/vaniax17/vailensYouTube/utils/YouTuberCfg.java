package ru.vaniax17.vailensYouTube.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.vaniax17.vailensYouTube.VailensYouTube;

import java.io.File;
import java.io.IOException;

public class YouTuberCfg {
    private static VailensYouTube plugin = VailensYouTube.getInst();
    private static File file = new File(plugin.getDataFolder() + File.separator + "youtubers.yml");
    private static FileConfiguration youtubersCfg = YamlConfiguration.loadConfiguration(file);

    public static void setYoutuberChannel(Player player, String link) {
        youtubersCfg.set(player.getName().toLowerCase() + ".ytChannel", link);
        try {
            youtubersCfg.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getYoutuberChannel(Player player) {
        return youtubersCfg.getString(player.getName().toLowerCase() + ".ytChannel");
    }


    public static void removeYoutuber(String playerName) {
        youtubersCfg.set(playerName.toLowerCase(), null);
        try {
            youtubersCfg.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean containsYoutuber(Player player) {
        return youtubersCfg.contains(player.getName().toLowerCase() + ".ytChannel");
    }
    public static boolean containsYoutuberForRemove(String playerName) {
        return youtubersCfg.contains(playerName.toLowerCase() + ".ytChannel");
    }


}
