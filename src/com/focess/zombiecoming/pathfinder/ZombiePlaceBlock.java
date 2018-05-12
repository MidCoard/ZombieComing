package com.focess.zombiecoming.pathfinder;

import java.util.List;

import org.bukkit.Material;

import com.focess.zombiecoming.utils.ZombieAIManager;

public interface ZombiePlaceBlock {

	static void addMaterial(List<Material> matersi) {
		ZombieAIManager.addPlace(matersi);
	}
	
	ZombiePlaceBlock addMater(List<Material> matersi);
	
}
