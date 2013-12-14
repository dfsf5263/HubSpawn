package com.kleptotech;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubSpawnCommandExec implements CommandExecutor{
	
	private HubSpawn plugin;
	 
	public HubSpawnCommandExec(HubSpawn plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hs")) {

			if(args.length < 1) return false;
			
			else if(args.length == 1){
				if(args[0].equalsIgnoreCase("reload")){
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (!player.hasPermission("hubspawn.admin")){
							sender.sendMessage(ChatColor.DARK_RED + "You don't have permssion for that.");
							return true;
						}
					}
					
					plugin.setX(plugin.getConfig().getDouble("x"));
					plugin.setY(plugin.getConfig().getDouble("y"));
					plugin.setZ(plugin.getConfig().getDouble("z"));
					plugin.setYaw(Float.parseFloat(plugin.getConfig().getString("yaw")));
					plugin.setPitch(Float.parseFloat(plugin.getConfig().getString("pitch")));
					plugin.setWorld(plugin.getServer().getWorld(plugin.getConfig().getString("world")));
					plugin.setSpawn(new Location(plugin.getWorld(), plugin.getX(), plugin.getY(), plugin.getZ(), plugin.getYaw(), plugin.getPitch()));
					if (!(sender instanceof Player)) {
						sender.sendMessage("Spawn Hub reloaded!");
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "Spawn Hub reloaded!");
					}
					return true;
				}
				else if(args[0].equalsIgnoreCase("setspawn")){
					if (!(sender instanceof Player)) {
						sender.sendMessage("This command can only be run by a player.");
					} else {
						Player player = (Player) sender;
						if (!player.hasPermission("hubspawn.admin")){
							sender.sendMessage(ChatColor.DARK_RED + "You don't have permssion for that.");
							return true;
						}
						Location location = player.getLocation();
						plugin.setX(location.getX());
						plugin.setY(location.getY());
						plugin.setZ(location.getZ());
						plugin.setYaw(location.getYaw());
						plugin.setPitch(location.getPitch());
						plugin.setWorld(location.getWorld());
						plugin.pushToConfig();
						plugin.setSpawn(new Location(plugin.getWorld(), plugin.getX(), plugin.getY(), plugin.getZ(), plugin.getYaw(), plugin.getPitch()));
						player.sendMessage(ChatColor.DARK_GREEN + "Spawn set!");
					}
					return true;
				}
				return false;
			}
		}
		
		//Default
		return false;
		
	}
}
