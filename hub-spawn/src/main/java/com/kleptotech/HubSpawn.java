package com.kleptotech;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HubSpawn extends JavaPlugin {
	
	double x,y,z;
	float yaw, pitch;
	World world;
	Location spawn;
   
	@Override
	public void onEnable() {
		getLogger().info("Hub Spawn is booting up. Hold on to your britches!");
		saveDefaultConfig();
		x = getConfig().getDouble("x");
		y = getConfig().getDouble("y");
		z = getConfig().getDouble("z");
		yaw = Float.parseFloat(getConfig().getString("yaw"));
		pitch = Float.parseFloat(getConfig().getString("pitch"));
		world = this.getServer().getWorld(getConfig().getString("world"));
		spawn = new Location(world, x, y, z, yaw, pitch);
		
		getServer().getPluginManager().registerEvents(new Listener() {
			
			@edu.umd.cs.findbugs.annotations.SuppressWarnings("UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS")
            @EventHandler
            public void playerJoin(PlayerJoinEvent event) {
            	event.getPlayer().teleport(spawn);
            }
            
        }, this);
		
		getCommand("hs").setExecutor(new HubSpawnCommandExec(this));
		
		getLogger().info("Hub Spawn is enabled! Whew...");
	}

	@Override
	public void onDisable() {
		getLogger().info("Hub Spawn is shutting down. Good Bye!");
		pushToConfig();
	}
	
	public void pushToConfig(){
		getConfig().set("x", x);
		getConfig().set("y", y);
		getConfig().set("z", z);
		getConfig().set("yaw", yaw);
		getConfig().set("pitch", pitch);
		getConfig().set("world", world.getName());
		saveConfig();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
}
