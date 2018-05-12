package com.focess.zombiecoming.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.focess.zombiecoming.pathfinder.*;

public class ZombieAIManager {
	
	private static final int ver;
	
	private static final int r;

	private static List<Material> place;

	private static List<Material> b;
	
	static {
		final String v = Bukkit.getServer().getClass().getPackage().getName();
		final String version = v.substring(v.lastIndexOf('.') + 1).split("_")[1];
		ver = Integer.parseInt(version);
		String rver = v.substring(v.lastIndexOf('.') + 1).split("_")[2].replace("R","");
		r = Integer.parseInt(rver);
	}
	
	public static ZombieAttackPlayer getAttack(Object obj) {
		if (ver == 12)
			return new Attack_1_12(obj);
		else if (ver == 11)
			return new Attack_1_11(obj);
		else if (ver == 10)
			return new Attack_1_10(obj);
		else if (ver == 9)
			return new Attack_1_9(obj);
		else if (ver == 8)
			if (r == 1)
				return new Attack_1_8_1(obj);
			else 
				return new Attack_1_8_2(obj);
		else if (ver == 7)
			if (r == 1)
				return new Attack_1_7_1(obj);
			else
				return new Attack_1_7_2(obj);
		return null;
	}

	public static ZombieBreakBlock getBreak(Object obj) {
		if (ver == 12)
			return new Break_1_12(obj).addMaterial(b);
		else if (ver == 11)
			return new Break_1_11(obj).addMaterial(b);
		else if (ver == 10)
			return new Break_1_10(obj).addMaterial(b);
		else if (ver == 9)
			return new Break_1_9(obj).addMaterial(b);
		else if (ver == 8)
			if (r == 1)
				return new Break_1_8_1(obj).addMaterial(b);
			else 
				return new Break_1_8_2(obj).addMaterial(b);
		else if (ver == 7)
			if (r == 1)
				return new Break_1_7_1(obj).addMaterial(b);
			else
				return new Break_1_7_2(obj).addMaterial(b);
		return null;
	}

	public static ZombiePlaceBlock getPlace(Object obj) {
		if (ver == 12)
			return new Place_1_12(obj).addMater(place);
		else if (ver == 11)
			return new Place_1_11(obj).addMater(place);
		else if (ver == 10)
			return new Place_1_10(obj).addMater(place);
		else if (ver == 9)
			return new Place_1_9(obj).addMater(place);
		else if (ver == 8)
			if (r == 1)
				return new Place_1_8_1(obj).addMater(place);
			else 
				return new Place_1_8_2(obj).addMater(place);
		else if (ver == 7)
			if (r == 1)
				return new Place_1_7_1(obj).addMater(place);
			else
				return new Place_1_7_2(obj).addMater(place);
		return null;
	}

	public static void addPlace(List<Material> mater) {
		place = mater;
	}
	
	public static void addBreak(List<Material> mater) {
		b = mater;
	}
}
