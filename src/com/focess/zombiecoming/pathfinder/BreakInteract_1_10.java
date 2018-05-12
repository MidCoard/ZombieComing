package com.focess.zombiecoming.pathfinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_10_R1.Block;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EntityZombie;
import net.minecraft.server.v1_10_R1.Navigation;
import net.minecraft.server.v1_10_R1.PathfinderGoal;

public abstract class BreakInteract_1_10 extends PathfinderGoal implements ZombieBreakBlockInteract {
	
	private static List<Material> maters = new ArrayList<>();

	public ZombieBreakBlock addMaterial(final List<Material> maters) {
		BreakInteract_1_10.maters = maters;
		return (ZombieBreakBlock) this;
	}

	protected Block block;
	protected BlockPosition blockPosition = BlockPosition.ZERO;
	boolean isStop;
	protected Collection<ItemStack> itemStacks;
	float x;
	float z;
	protected EntityZombie zombie;

	public BreakInteract_1_10(final EntityZombie zombie) {
		this.zombie = zombie;
		if (!(zombie.getNavigation() instanceof Navigation))
			throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
	}

	@Override
	public boolean a() {
		final org.bukkit.block.Block block = this.getBlock((Zombie) this.zombie.getBukkitEntity());
		if (block != null) {
			this.itemStacks = block.getDrops();
			this.block = CraftMagicNumbers.getBlock(block);
			this.blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
			return true;
		} else
			return false;
	}

	@Override
	public boolean b() {
		return !this.isStop;
	}

	@Override
	public void c() {
		this.isStop = false;
		this.x = (float) (this.blockPosition.getX() + 0.5F - this.zombie.locX);
		this.z = (float) (this.blockPosition.getZ() + 0.5F - this.zombie.locZ);
	}

	@Override
	public void e() {
		final float f1 = (float) (this.blockPosition.getX() + 0.5F - this.zombie.locX);
		final float f2 = (float) (this.blockPosition.getZ() + 0.5F - this.zombie.locZ);
		final float f3 = this.x * f1 + this.z * f2;
		if (f3 < 0.0F)
			this.isStop = true;
	}

	private org.bukkit.block.Block getBlock(final Zombie zombie) {
		final Location loc = zombie.getLocation();
		final float pitch = loc.getPitch();
		final float yaw = loc.getYaw();
		if (pitch <= -30)
			if (yaw >= 0 && yaw < 90)
				for (int i = zombie.getLocation().getBlockX(); i > zombie.getLocation().getBlockX() - 2; i--)
					for (int j = zombie.getLocation().getBlockZ(); i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() + 2, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY() + 3, j)
								.getBlock();
						final org.bukkit.block.Block b6 = new Location(loc.getWorld(), i, loc.getBlockY() + 4, j)
								.getBlock();
						if (!b6.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b6.getType()))
							return b6;
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						continue;
					}
			else if (yaw >= 90 && yaw < 180)
				for (int i = zombie.getLocation().getBlockX(); i > zombie.getLocation().getBlockX() - 2; i--)
					for (int j = zombie.getLocation().getBlockZ(); i > zombie.getLocation().getBlockZ() - 2; j--) {
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() + 2, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY() + 3, j)
								.getBlock();
						final org.bukkit.block.Block b6 = new Location(loc.getWorld(), i, loc.getBlockY() + 4, j)
								.getBlock();
						if (!b6.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b6.getType()))
							return b6;
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						continue;
					}
			else if (yaw >= 180 && yaw < 270)
				for (int i = zombie.getLocation().getBlockX(); i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ(); i > zombie.getLocation().getBlockZ() - 2; j--) {
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() + 2, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY() + 3, j)
								.getBlock();
						final org.bukkit.block.Block b6 = new Location(loc.getWorld(), i, loc.getBlockY() + 4, j)
								.getBlock();
						if (!b6.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b6.getType()))
							return b6;
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						continue;
					}
			else if (yaw >= 270)
				for (int i = zombie.getLocation().getBlockX(); i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ(); i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() + 2, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY() + 3, j)
								.getBlock();
						final org.bukkit.block.Block b6 = new Location(loc.getWorld(), i, loc.getBlockY() + 4, j)
								.getBlock();
						if (!b6.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b6.getType()))
							return b6;
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						continue;
					}
			else
				return null;
		else if (pitch < 30 && pitch > -30)
			if (yaw >= 0 && yaw < 90)
				for (int i = zombie.getLocation().getBlockX() - 1; i > zombie.getLocation().getBlockX() - 2; i--)
					for (int j = zombie.getLocation().getBlockZ() + 1; i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 90 && yaw < 180)
				for (int i = zombie.getLocation().getBlockX() - 1; i > zombie.getLocation().getBlockX() - 2; i--)
					for (int j = zombie.getLocation().getBlockZ() - 1; i > zombie.getLocation().getBlockZ() - 2; j--) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 180 && yaw < 270)
				for (int i = zombie.getLocation().getBlockX() + 1; i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ() - 1; i > zombie.getLocation().getBlockZ() - 2; j--) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 270)
				for (int i = zombie.getLocation().getBlockX() + 1; i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ() + 1; i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() + 1, j)
								.getBlock();
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else
				return null;
		else if (pitch >= 30)
			if (yaw >= 0 && yaw < 90)
				for (int i = zombie.getLocation().getBlockX(); i > zombie.getLocation().getBlockX() - 2; i--)
					for (int j = zombie.getLocation().getBlockZ(); i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 4, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY() - 3, j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() - 2, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 90 && yaw < 180)
				for (int i = zombie.getLocation().getBlockX(); i > zombie.getLocation().getBlockX() - 1; i--)
					for (int j = zombie.getLocation().getBlockZ(); i > zombie.getLocation().getBlockZ() - 1; j--) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 4, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY() - 3, j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() - 2, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 180 && yaw < 270)
				for (int i = zombie.getLocation().getBlockX(); i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ(); i > zombie.getLocation().getBlockZ() - 2; j--) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 4, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY() - 3, j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() - 2, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else if (yaw >= 270)
				for (int i = zombie.getLocation().getBlockX(); i < zombie.getLocation().getBlockX() + 2; i++)
					for (int j = zombie.getLocation().getBlockZ(); i < zombie.getLocation().getBlockZ() + 2; j++) {
						final org.bukkit.block.Block b1 = new Location(loc.getWorld(), i, loc.getBlockY() - 4, j)
								.getBlock();
						final org.bukkit.block.Block b2 = new Location(loc.getWorld(), i, loc.getBlockY() - 3, j)
								.getBlock();
						final org.bukkit.block.Block b3 = new Location(loc.getWorld(), i, loc.getBlockY() - 2, j)
								.getBlock();
						final org.bukkit.block.Block b4 = new Location(loc.getWorld(), i, loc.getBlockY() - 1, j)
								.getBlock();
						final org.bukkit.block.Block b5 = new Location(loc.getWorld(), i, loc.getBlockY(), j)
								.getBlock();
						if (!b5.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b5.getType()))
							return b5;
						if (!b4.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b4.getType()))
							return b4;
						if (!b3.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b3.getType()))
							return b3;
						if (!b2.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b2.getType()))
							return b2;
						if (!b1.getType().equals(Material.AIR)
								&& BreakInteract_1_10.maters.contains(b1.getType()))
							return b1;
						continue;
					}
			else
				return null;
		return null;
	}
}