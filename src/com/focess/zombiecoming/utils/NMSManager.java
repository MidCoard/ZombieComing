package com.focess.zombiecoming.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class NMSManager {

	private static final String version;

	private static final Method getHandle;

	static {
		version = Bukkit.getServer().getClass().getName().replace(".CraftServer", "").replace("org.bukkit.craftbukkit.",
				"");
		Method m = null;
		try {
			m = getCBClass("entity.CraftEntity").getDeclaredMethod("getHandle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getHandle = m;
	}

	public static Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getHandle(Entity entity) {
		try {
			return getHandle.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class<?> getCBClass(String name) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getNMSField(String name, String field) {
		for (Field f : getNMSClass(name).getDeclaredFields())
			if (f.getName().equals(field))
				try {
					return f.get(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		return null;
	}

}
