package ru.avenux.flyswitcher;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static org.bukkit.GameMode.*;

public final class flyswitcher extends JavaPlugin implements Listener {
    private final Logger log = this.getLogger();

    @Override
    public void onEnable() {
        log.info("Plugin enabled!");
        log.info("By Avenux");
        this.getCommand("flyon").setExecutor(this);
        this.getCommand("flyoff").setExecutor(this);
        this.getCommand("survivalmode").setExecutor(this);
    }

    @Override
    public void onDisable() {
        log.info("Plugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player e = (Player) sender;
        if(command.getName().equals(("flyon"))) {
            flyyes(e);
            return true;
        }
        if(command.getName().equals(("flyoff"))) {
            flyno(e);
            return true;
        }
        if(command.getName().equals(("survivalmode"))) {
            survivalmode(e);
            return true;
        }
        return false;
    }
    void flyyes (Player e) {
        if(e.hasPermission("fly.avenux")){
            e.setAllowFlight(true);
            e.setFlying(true);
            e.sendMessage(ChatColor.DARK_GREEN + "• " + e.getName() + ChatColor.WHITE + " - " + ChatColor.GREEN + "turned on the fly.");
        }
        {
            if (!e.hasPermission("fly.avenux")){
                e.sendMessage(ChatColor.RED + "You don't have permission to do this.\nIf you get stuck in the fly, then write /survivalmode to switch in survival mode and off fly.");
            }
        }
    }
    void flyno(Player e) {
        if(e.hasPermission("fly.avenux")){
            e.setAllowFlight(false);
            e.setFlying(false);
            e.sendMessage(ChatColor.DARK_GREEN + "• " + e.getName() + ChatColor.WHITE + " - " + ChatColor.GREEN + "turned off the fly.");
        }
        {
            if (!e.hasPermission("fly.avenux")){
                e.sendMessage(ChatColor.RED + "You don't have permission to do this.\nIf you get stuck in the fly, then write /survivalmode to switch in survival mode and off fly.");
                }
        }
    }
    void survivalmode(Player e) {
            if (e.getGameMode() == CREATIVE || e.getGameMode() == SPECTATOR || e.getGameMode() == ADVENTURE && !e.hasPermission("fly.avenux")
            ) {
                e.setFlying(false);
                e.setAllowFlight(false);
                e.setGameMode(SURVIVAL);
            }
    }
}

