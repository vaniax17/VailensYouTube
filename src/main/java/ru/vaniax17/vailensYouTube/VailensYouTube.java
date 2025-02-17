package ru.vaniax17.vailensYouTube;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.vaniax17.vailensYouTube.commands.YTCommand;
import ru.vaniax17.vailensYouTube.utils.TabCompleterYTCommand;

import java.io.File;

public final class VailensYouTube extends JavaPlugin {

    @Getter
    public static VailensYouTube inst;

    @Override
    public void onEnable() {
        inst = this;

        File configCfg = new File(getDataFolder() + File.separator + "config.yml");
        {
            if (!configCfg.exists()) {
                saveResource("config.yml", false);
            }
        }
        File langCfg = new File(getDataFolder() + File.separator + "lang.yml");
        {
            if (!langCfg.exists()) {
                saveResource("lang.yml", false);
            }
        }

        File youtubersCfg = new File(getDataFolder() + File.separator + "youtubers.yml");
        {
            if (!youtubersCfg.exists()) {
                saveResource("youtubers.yml", false);
            }
        }
        getCommand("yt").setExecutor(new YTCommand());
        getCommand("yt").setTabCompleter(new TabCompleterYTCommand());
    }



    @Override
    public void onDisable() {

    }
}

