package com.focess.zombiecoming.pathfinder;

import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntityZombie;
import net.minecraft.server.v1_7_R4.PathfinderGoal;

public class Attack_1_7_2 extends PathfinderGoal implements ZombieAttackPlayer {

	private EntityPlayer attack;

	private final EntityZombie zombie;

	public Attack_1_7_2(final Object obj) {
		this.zombie = (EntityZombie) obj;
	}

	@Override
	public boolean a() {
		Player p = null;
		double d = 100;
		for (final Player player : this.zombie.getBukkitEntity().getWorld().getPlayers())
			if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE))
				if (player.getLocation().distance(this.zombie.getBukkitEntity().getLocation()) < d) {
					d = player.getLocation().distance(this.zombie.getBukkitEntity().getLocation());
					p = player;
				}
		if (p != null)
			this.attack = ((CraftPlayer) p).getHandle();
		else
			return false;
		return true;
	}

	@Override
	public void e() {
		this.zombie.setGoalTarget(this.attack);
	}

}
