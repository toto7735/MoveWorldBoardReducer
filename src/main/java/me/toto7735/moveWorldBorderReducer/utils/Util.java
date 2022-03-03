package me.toto7735.moveWorldBorderReducer.utils;

import org.bukkit.World;

public class Util {

    public static void reduceWorldBorder(World world) {
        world.getWorldBorder().setSize(world.getWorldBorder().getSize() - 1);
    }

}
