package me.toto7735.moveWorldBorderReducer;

import me.toto7735.moveWorldBorderReducer.listener.Listener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class MoveWorldBorderReducer extends JavaPlugin {

    private static MoveWorldBorderReducer moveWorldBorderReducer;

    @Override
    public void onEnable() {
        System.out.println("Enabled");
        moveWorldBorderReducer = this;

        for (World world : Bukkit.getWorlds()) {
            world.getWorldBorder().setSize(300);
        }
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled");
    }

    public static MoveWorldBorderReducer getMoveWorldBorderReducer() {
        return moveWorldBorderReducer;
    }

}
