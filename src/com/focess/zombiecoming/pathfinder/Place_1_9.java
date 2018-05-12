package com.focess.zombiecoming.pathfinder;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_9_R2.EntityZombie;
import net.minecraft.server.v1_9_R2.PathfinderGoal;

public class Place_1_9 extends PathfinderGoal implements ZombiePlaceBlock {

	private static List<Material> maters;

	private final EntityZombie zombie;

	public Place_1_9(final Object obj) {
		this.zombie = (EntityZombie) obj;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean a() {
		final Zombie z = (Zombie) this.zombie.getBukkitEntity();
		return z.getEquipment() != null && z.getEquipment().getItemInHand() != null
				&& !z.getEquipment().getItemInHand().getType().equals(Material.AIR)
				&& Place_1_9.maters.contains(z.getEquipment().getItemInHand().getType());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void e() {
		final Zombie z = (Zombie) this.zombie.getBukkitEntity();
		if (z.getEquipment().getItemInHand().getType().isBlock()) {
			final List<Player> players = new ArrayList<>();
			double d = 200;
			Player p = null;
			for (final Entity entity : z.getNearbyEntities(200, 200, 200))
				if (entity instanceof Player)
					players.add((Player) entity);
			for (final Player player : players)
				if (player.getLocation().distance(z.getLocation()) < d) {
					d = player.getLocation().distance(z.getLocation());
					p = player;
				}
			if (p != null)
				if (p.getLocation().getBlockY() > z.getLocation().getBlockY()) {
					Location l = z.getLocation().clone();
					l.setY(l.getBlockY() + 2);
					if (l.getBlock().getType().equals(Material.AIR)) {
						Location zombieLocation = z.getLocation().clone();
						zombieLocation.setY(zombieLocation.getBlockY() + 1);
						z.getLocation().getBlock().setType(z.getEquipment().getItemInHand().getType());
						z.getLocation().getBlock().setData((byte) z.getEquipment().getItemInHand().getDurability());
						z.teleport(zombieLocation);
						if (z.getEquipment().getItemInHand().getAmount() == 1)
							z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
						else {
							ItemStack itemStack = new ItemStack(z.getEquipment().getItemInHand().getType(),
									z.getEquipment().getItemInHand().getAmount() - 1,
									z.getEquipment().getItemInHand().getDurability());
							z.getEquipment().setItemInHand(itemStack);
						}
					}
				} else if (p.getLocation().getBlockY() == z.getLocation().getBlockY())
					if (p.getLocation().getBlockX() > z.getLocation().getBlockX()) {
						final Location loc = z.getLocation().clone();
						loc.setY(loc.getBlockY() - 1);
						loc.setX(loc.getBlockX() + 1);
						if (loc.getBlock().getType().equals(Material.AIR)) {
							loc.getBlock().setType(z.getEquipment().getItemInHand().getType());
							loc.getBlock().setData((byte) z.getEquipment().getItemInHand().getDurability());
							if (z.getEquipment().getItemInHand().getAmount() == 1)
								z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
							else {
								ItemStack itemStack = new ItemStack(z.getEquipment().getItemInHand().getType(),
										z.getEquipment().getItemInHand().getAmount() - 1,
										z.getEquipment().getItemInHand().getDurability());
								z.getEquipment().setItemInHand(itemStack);
							}
						}
					} else if (p.getLocation().getBlockX() < z.getLocation().getBlockX()) {
						final Location loc = z.getLocation().clone();
						loc.setY(loc.getBlockY() - 1);
						loc.setX(loc.getBlockX() - 1);
						if (loc.getBlock().getType().equals(Material.AIR)) {
							loc.getBlock().setType(z.getEquipment().getItemInHand().getType());
							loc.getBlock().setData((byte) z.getEquipment().getItemInHand().getDurability());
							if (z.getEquipment().getItemInHand().getAmount() == 1)
								z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
							else {
								ItemStack itemStack = new ItemStack(z.getEquipment().getItemInHand().getType(),
										z.getEquipment().getItemInHand().getAmount() - 1,
										z.getEquipment().getItemInHand().getDurability());
								z.getEquipment().setItemInHand(itemStack);
							}
						}
					} else if (p.getLocation().getBlockZ() > z.getLocation().getBlockZ()) {
						final Location loc = z.getLocation().clone();
						loc.setY(loc.getBlockY() - 1);
						loc.setX(loc.getBlockZ() + 1);
						if (loc.getBlock().getType().equals(Material.AIR)) {
							loc.getBlock().setType(z.getEquipment().getItemInHand().getType());
							loc.getBlock().setData((byte) z.getEquipment().getItemInHand().getDurability());
							if (z.getEquipment().getItemInHand().getAmount() == 1)
								z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
							else {
								ItemStack itemStack = new ItemStack(z.getEquipment().getItemInHand().getType(),
										z.getEquipment().getItemInHand().getAmount() - 1,
										z.getEquipment().getItemInHand().getDurability());
								z.getEquipment().setItemInHand(itemStack);
							}
						}
					} else if (p.getLocation().getBlockZ() < z.getLocation().getBlockZ()) {
						final Location loc = z.getLocation().clone();
						loc.setY(loc.getBlockY() - 1);
						loc.setX(loc.getBlockZ() - 1);
						if (loc.getBlock().getType().equals(Material.AIR)) {
							loc.getBlock().setType(z.getEquipment().getItemInHand().getType());
							loc.getBlock().setData((byte) z.getEquipment().getItemInHand().getDurability());
							if (z.getEquipment().getItemInHand().getAmount() == 1)
								z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
							else {
								ItemStack itemStack = new ItemStack(z.getEquipment().getItemInHand().getType(),
										z.getEquipment().getItemInHand().getAmount() - 1,
										z.getEquipment().getItemInHand().getDurability());
								z.getEquipment().setItemInHand(itemStack);
							}
						}
					}
		}
	}

	@Override
	public ZombiePlaceBlock addMater(List<Material> maters) {
		Place_1_9.maters = maters;
		return this;
	}

}
