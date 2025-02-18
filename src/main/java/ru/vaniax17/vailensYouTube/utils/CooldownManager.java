package ru.vaniax17.vailensYouTube.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();
    private final long cooldownDuration;
    public CooldownManager(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
    }
    public boolean isOnCooldown(Player player, String command) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(command) || !cooldowns.get(command).containsKey(playerId)) {
            return false;
        }

        long lastUsed = cooldowns.get(command).get(playerId);
        return (System.currentTimeMillis() - lastUsed) < cooldownDuration;
    }

    public long getRemainingCooldown(Player player, String command) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(command) || !cooldowns.get(command).containsKey(playerId)) {
            return 0;
        }

        long lastUsed = cooldowns.get(command).get(playerId);
        long elapsed = System.currentTimeMillis() - lastUsed;

        return Math.max(0, cooldownDuration - elapsed);
    }
    public void setCooldown(Player player, String command) {
        cooldowns.putIfAbsent(command, new HashMap<>());
        cooldowns.get(command).put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void removeCooldown(Player player, String command) {
        if (cooldowns.containsKey(command)) {
            cooldowns.get(command).remove(player.getUniqueId());
        }
    }
    public void clearAllCooldowns() {
        cooldowns.clear();
    }

    public void clearCooldownsForCommand(String command) {
        cooldowns.remove(command);
    }
}
