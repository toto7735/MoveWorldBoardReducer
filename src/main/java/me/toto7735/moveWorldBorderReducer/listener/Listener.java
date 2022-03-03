package me.toto7735.moveWorldBorderReducer.listener;

import me.toto7735.moveWorldBorderReducer.MoveWorldBorderReducer;
import me.toto7735.moveWorldBorderReducer.utils.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Listener implements org.bukkit.event.Listener {

    private Map<Player, Integer> runnableMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getX() == event.getFrom().getX() && event.getTo().getY() == event.getFrom().getY() && event.getTo().getZ() == event.getFrom().getZ()) return;
        Util.reduceWorldBorder(event.getPlayer().getWorld());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        runnableMap.put(event.getPlayer(), new BukkitRunnable() {
            public void run() {
                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§lCurrent World Border Size§f: §e" + event.getPlayer().getWorld().getWorldBorder().getSize()));
            }
        }.runTaskTimerAsynchronously(MoveWorldBorderReducer.getMoveWorldBorderReducer(), 0, 1).getTaskId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().cancelTask(runnableMap.get(event.getPlayer()));
        runnableMap.remove(event.getPlayer());
    }

}
