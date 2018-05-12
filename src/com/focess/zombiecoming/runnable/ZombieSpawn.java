package com.focess.zombiecoming.runnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import com.focess.zombiecoming.ZombieComing;
import com.focess.zombiecoming.utils.NMSManager;
import com.focess.zombiecoming.utils.ZombieAIManager;

public class ZombieSpawn extends BukkitRunnable {

	private static final Field goalSelector;

	private static final Method a;

	private static final Method getAttributeMap;

	private static final Method setValue;

	private static final Method a1;

	static {
		Field f = null;
		try {
			f = NMSManager.getNMSClass("EntityInsentient").getDeclaredField("goalSelector");
			f.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goalSelector = f;
		Method m = null;
		try {
			m = NMSManager.getNMSClass("PathfinderGoalSelector").getMethod("a",
					new Class[] { int.class, NMSManager.getNMSClass("PathfinderGoal") });
		} catch (Exception e) {
			e.printStackTrace();
		}
		a = m;
		Method m1 = null;
		try {
			m1 = NMSManager.getNMSClass("EntityLiving").getMethod("getAttributeMap");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getAttributeMap = m1;
		Method m2 = null;
		try {
			m2 = NMSManager.getNMSClass("AttributeInstance").getMethod("setValue", double.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setValue = m2;
		Method m3 = null;
		try {
			m3 = NMSManager.getNMSClass("AttributeMapBase").getMethod("a", String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		a1 = m3;
	}

	public static List<Zombie> zombies = new ArrayList<>();

	private final int amount;

	private final double armor;

	private final double attack;

	private final double health;

	private final String message;

	private final boolean notice;

	private final List<Integer> randoms = new ArrayList<>();

	private final List<World> registerWorlds = new ArrayList<>();

	private final double speed;

	private final int target_time;

	private final HashMap<World, Integer> time = new HashMap<>();

	public ZombieSpawn(final ZombieComing coming) {
		for (final String world : coming.getConfig().getStringList("Worlds")) {
			final World w = Bukkit.getWorld(world);
			if (w != null) {
				this.registerWorlds.add(w);
				this.time.put(w, 0);
			}
		}
		final int max = coming.getConfig().getInt("RandomMax");
		final int min = coming.getConfig().getInt("RandomMin");
		for (int i = min; i < max; i++) {
			this.randoms.add(i);
			this.randoms.add(-i);
		}
		this.target_time = coming.getConfig().getInt("Time");
		this.amount = coming.getConfig().getInt("Amount");
		this.health = coming.getConfig().getDouble("Health");
		this.attack = coming.getConfig().getDouble("Attack");
		this.notice = coming.getConfig().getBoolean("Notice");
		this.message = coming.getConfig().getString("Message");
		this.speed = coming.getConfig().getDouble("Speed");
		this.armor = coming.getConfig().getDouble("Armor");
	}

	private Location randomLocation(final Player player) {
		final int x = this.randoms.get(new Random().nextInt(this.randoms.size()));
		final int z = this.randoms.get(new Random().nextInt(this.randoms.size()));
		return player.getWorld()
				.getHighestBlockAt(player.getLocation().getBlockX() + x, player.getLocation().getBlockZ() + z)
				.getLocation();
	}

	@Override
	public void run() {
		for (final World world : this.registerWorlds)
			if (world.getTime() % 24000 > 12000) {
				this.time.put(world, this.time.get(world) + 1);
				if (this.time.get(world) == 1)
					for (final Player player : world.getPlayers()) {
						for (int i = 0; i < this.amount; i++) {
							final Zombie zombie = (Zombie) player.getWorld().spawnEntity(this.randomLocation(player),
									EntityType.ZOMBIE);
							zombie.setCanPickupItems(true);
							Object handle = NMSManager.getHandle(zombie);
							try {
								a.invoke(goalSelector.get(handle), 1, ZombieAIManager.getBreak(handle));
								a.invoke(goalSelector.get(handle), 1, ZombieAIManager.getPlace(handle));
								a.invoke(goalSelector.get(handle), 2, ZombieAIManager.getAttack(handle));
								Object a = a1.invoke(getAttributeMap.invoke(handle), "generic.attackDamage");
								Object h = a1.invoke(getAttributeMap.invoke(handle), "generic.maxHealth");
								Object m = a1.invoke(getAttributeMap.invoke(handle), "generic.movementSpeed");
								setValue.invoke(a, attack);
								setValue.invoke(h, health);
								setValue.invoke(m, speed);
								final String v = Bukkit.getServer().getClass().getPackage().getName();
								final String version = v.substring(v.lastIndexOf('.') + 1).split("_")[1];
								if (Integer.parseInt(version) >= 9) {
									Object g = a1.invoke(getAttributeMap.invoke(handle), "generic.armor");
									setValue.invoke(g, armor);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							ZombieSpawn.zombies.add(zombie);
						}
						if (this.notice)
							player.sendMessage(this.message);
					}
				if (this.time.get(world) > this.target_time)
					this.time.put(world, 0);
			}
	}

}
