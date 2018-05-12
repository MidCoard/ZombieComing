package com.focess.zombiecoming.pathfinder;

import java.util.List;

import org.bukkit.Material;

import com.focess.zombiecoming.utils.ZombieAIManager;

public abstract interface ZombieBreakBlockInteract {

	static void addMaterial(List<Material> matersb) {
		ZombieAIManager.addBreak(matersb);
	}

}