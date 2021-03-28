package lol.hyper.servercore.tools;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class FuckWitherSkulls {

    static int mobsKilled;

    public static void killWitherSkulls() {
        mobsKilled = 0;

        World world = Bukkit.getWorlds().get(0);
        World nether = Bukkit.getWorlds().get(1);
        World end = Bukkit.getWorlds().get(2);

        for (Entity entities : world.getEntities()) {
            if (entities.getType().equals(EntityType.WITHER_SKULL)) {
                entities.remove();
                mobsKilled++;
            }
        }

        for (Entity entities : nether.getEntities()) {
            if (entities.getType().equals(EntityType.WITHER_SKULL)) {
                entities.remove();
                mobsKilled++;
            }
        }

        for (Entity entities : end.getEntities()) {
            if (entities.getType().equals(EntityType.WITHER_SKULL)) {
                entities.remove();
                mobsKilled++;
            }
        }
    }

    public static int countSkulls() {
        return mobsKilled;
    }
}
