package ru.vaniax17.vailensYouTube.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    // Хранилище для отслеживания задержек у игроков (по командам и аргументам)
    private final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    // Длительность задержки (в миллисекундах)
    private final long cooldownDuration;

    // Конструктор для установки длительности задержки
    public CooldownManager(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
    }

    /**
     * Проверяет, находится ли игрок в режиме задержки для определённой команды.
     *
     * @param player Игрок для проверки.
     * @param command Ключ команды или аргумента.
     * @return true, если игрок находится в режиме задержки, иначе false.
     */
    public boolean isOnCooldown(Player player, String command) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(command) || !cooldowns.get(command).containsKey(playerId)) {
            return false;
        }

        long lastUsed = cooldowns.get(command).get(playerId);
        return (System.currentTimeMillis() - lastUsed) < cooldownDuration;
    }

    /**
     * Возвращает оставшееся время задержки для игрока по определённой команде.
     *
     * @param player Игрок для проверки.
     * @param command Ключ команды или аргумента.
     * @return Оставшееся время в миллисекундах или 0, если задержки нет.
     */
    public long getRemainingCooldown(Player player, String command) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(command) || !cooldowns.get(command).containsKey(playerId)) {
            return 0;
        }

        long lastUsed = cooldowns.get(command).get(playerId);
        long elapsed = System.currentTimeMillis() - lastUsed;

        return Math.max(0, cooldownDuration - elapsed);
    }

    /**
     * Устанавливает задержку для игрока по определённой команде.
     *
     * @param player Игрок, для которого устанавливается задержка.
     * @param command Ключ команды или аргумента.
     */
    public void setCooldown(Player player, String command) {
        cooldowns.putIfAbsent(command, new HashMap<>());
        cooldowns.get(command).put(player.getUniqueId(), System.currentTimeMillis());
    }

    /**
     * Удаляет задержку для игрока по определённой команде.
     *
     * @param player Игрок, для которого удаляется задержка.
     * @param command Ключ команды или аргумента.
     */
    public void removeCooldown(Player player, String command) {
        if (cooldowns.containsKey(command)) {
            cooldowns.get(command).remove(player.getUniqueId());
        }
    }

    /**
     * Полностью очищает все задержки для всех команд.
     */
    public void clearAllCooldowns() {
        cooldowns.clear();
    }

    /**
     * Полностью очищает задержки для определённой команды.
     *
     * @param command Ключ команды или аргумента.
     */
    public void clearCooldownsForCommand(String command) {
        cooldowns.remove(command);
    }
}
