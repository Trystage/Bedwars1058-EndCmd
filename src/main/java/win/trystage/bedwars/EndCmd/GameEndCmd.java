package win.trystage.bedwars.EndCmd;

import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class GameEndCmd extends JavaPlugin implements Listener {

    private static List<String> commands;
    private static String type;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        commands = this.getConfig().getStringList("command");
        type = this.getConfig().getString("type");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event){
        runCmd(event.getArena().getWorld());
    }

    private static void runCmd(World world){
        switch (type){
            case "PLAYER":
                for (Player player : world.getPlayers()){
                    commands.forEach(player::performCommand);
                }
                break;
            case "CONSOLE":
                commands.forEach(command -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),command));
        }
    }
}
