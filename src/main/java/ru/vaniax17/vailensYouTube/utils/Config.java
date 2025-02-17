package ru.vaniax17.vailensYouTube.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.vaniax17.vailensYouTube.VailensYouTube;

import java.io.File;

public class Config {
    private VailensYouTube plugin = VailensYouTube.getInst();
    private File file = new File(plugin.getDataFolder() + File.separator + "config.yml");
    private FileConfiguration configCfg = YamlConfiguration.loadConfiguration(file);

    @Getter
    long cooldown;

    public Config(VailensYouTube plugin) {
        this.plugin = plugin;
        cooldown = configCfg.getLong("cooldown");

    }


}
