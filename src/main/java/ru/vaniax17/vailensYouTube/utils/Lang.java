package ru.vaniax17.vailensYouTube.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.vaniax17.vailensYouTube.VailensYouTube;

import java.io.File;
import java.util.List;

public class Lang {
    private VailensYouTube plugin = VailensYouTube.getInst();
    private File file = new File(plugin.getDataFolder() + File.separator + "lang.yml");
    private FileConfiguration langCfg = YamlConfiguration.loadConfiguration(file);

    String consoleMsg;
    String permAddToYouTubers;
    String minArgs;
    String successAddYouTuber;
    String permRemoveToYouTubers;
    String successRemoveYouTuber;
    String notYouTubersFile;
    @Getter
    List<String> messageForYouTubers;
    String messageCooldown;
    String youtuberForRemoveUndefined;
    String alreadyInFile;
    String playerMustBeOnline;

    public Lang(VailensYouTube plugin) {
        this.plugin = plugin;
        consoleMsg = langCfg.getString("consoleMsg");
        permAddToYouTubers = langCfg.getString("permAddToYouTubers");
        minArgs = langCfg.getString("minArgs");
        successAddYouTuber = langCfg.getString("successAddYouTuber");
        permRemoveToYouTubers = langCfg.getString("permRemoveToYouTubers");
        successRemoveYouTuber = langCfg.getString("successRemoveYouTuber");
        notYouTubersFile = langCfg.getString("notYouTubersFile");
        messageForYouTubers = langCfg.getStringList("messageForYouTubers");
        messageCooldown = langCfg.getString("messageCooldown");
        youtuberForRemoveUndefined = langCfg.getString("youtuberForRemoveUndefined");
        alreadyInFile = langCfg.getString("alreadyInFile");
        playerMustBeOnline = langCfg.getString("playerMustBeOnline");

    }

    public String ChatColor(String text) {
        return Hex.toChatColorString(text);
    }

    public String getConsoleMsg() {
        return ChatColor(consoleMsg);
    }

    public String getPermAddToYouTubers() {
        return ChatColor(permAddToYouTubers);
    }

    public String getMinArgs() {
        return ChatColor(minArgs);
    }

    public String getSuccessAddYouTuber() {
        return ChatColor(successAddYouTuber);
    }

    public String getPermRemoveToYouTubers() {
        return ChatColor(permRemoveToYouTubers);
    }

    public String getSuccessRemoveYouTuber() {
        return ChatColor(successRemoveYouTuber);
    }

    public String getNotYouTubersFile() {
        return ChatColor(notYouTubersFile);
    }

    public String getMessageCooldown() {
        return ChatColor(messageCooldown);
    }

    public String getYoutuberForRemoveUndefined() {
        return ChatColor(youtuberForRemoveUndefined);
    }

    public String getAlreadyInFile() {
        return ChatColor(alreadyInFile);
    }

    public String getPlayerMustBeOnline() {
        return ChatColor(playerMustBeOnline);
    }
}
