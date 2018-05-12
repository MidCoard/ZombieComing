package com.focess.zombiecoming.pathfinder;

import java.util.List;

import org.bukkit.Material;

public abstract interface ZombieBreakBlock extends ZombieBreakBlockInteract {
	
	ZombieBreakBlock addMaterial(List<Material> matersb);
	
}