package com.focess.zombiecoming;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import com.focess.zombiecoming.pathfinder.ZombieBreakBlockInteract;
import com.focess.zombiecoming.pathfinder.ZombiePlaceBlock;
import com.focess.zombiecoming.runnable.ZombieSpawn;

public class ZombieComing extends JavaPlugin {

	private void loadConfig() {
		if (!this.getDataFolder().exists())
			this.getDataFolder().mkdir();
		final File file = new File(this.getDataFolder(), "config.yml");
		if (!file.exists())
			this.saveDefaultConfig();
		this.reloadConfig();
	}

	@Override
	public void onDisable() {
		this.getLogger().info("ZombieComing载入成功");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		this.getLogger().info("ZombieComing载出成功");
		this.loadConfig();
		final List<Material> matersb = new ArrayList<>();
		for (final int mater : this.getConfig().getIntegerList("Blocks"))
			matersb.add(Material.getMaterial(mater));
		ZombieBreakBlockInteract.addMaterial(matersb);
		final List<Material> matersi = new ArrayList<>();
		for (final int mater : this.getConfig().getIntegerList("Items"))
			matersi.add(Material.getMaterial(mater));
		ZombiePlaceBlock.addMaterial(matersi);
		this.getServer().getScheduler().runTaskTimer(this, (Runnable) new ZombieSpawn(this), 0, 20);
	}

}
