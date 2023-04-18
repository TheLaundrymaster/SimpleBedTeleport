package me.laundrymaster.bedteleport;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Bed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class BedTeleport extends JavaPlugin implements Listener {

    Location l;
    Location temp;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBedSetSpawn(PlayerBedEnterEvent e){

        Player p = e.getPlayer();

        l = p.getBedSpawnLocation();
        temp = l;

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("bed") || command.getName().equalsIgnoreCase("home")){

            if(sender instanceof Player){
                Player p = (Player) sender;

                l = p.getBedSpawnLocation();


                if(this.l != null){
                    p.teleport(l);
                    p.spawnParticle(Particle.DRAGON_BREATH, p.getLocation(),150);
                    p.sendMessage(ChatColor.GOLD + "You have been teleported to your bed!");
                }else{
                    p.sendMessage(ChatColor.RED + "Last bed has been destroyed.\n" + "The last known coordinates: " +

                            (temp.getX())+ " : " + (temp.getY()) + " : " + (temp.getZ()));
                }





            }



        }





        return true;
    }
}
