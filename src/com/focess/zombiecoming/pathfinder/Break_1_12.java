package com.focess.zombiecoming.pathfinder;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.EntityZombie;

public class Break_1_12 extends BreakInteract_1_12 implements ZombieBreakBlock {
	
	private int breaksit = -1;
	private int breakTime;

	public Break_1_12(final Object obj) {
		super((EntityZombie) obj);
	}

	@Override
	public boolean a() {
		if (!super.a())
			return false;
		if (!this.zombie.world.getGameRules().getBoolean("mobGriefing"))
			return false;
		return true;
	}

	@Override
	public boolean b() {
		if (this.breakTime <= 30)
			return true;
		return false;
	}

	@Override
	public void c() {
		super.c();
		this.breakTime = 0;
	}

	@Override
	public void d() {
		super.d();
		this.zombie.world.c(this.zombie.getId(), this.blockPosition, -1);
	}

	@Override
	public void e() {
		super.e();
		this.breakTime += 1;
		final int breaksit = (int) (this.breakTime / 3F);
		if (breaksit != this.breaksit) {
			this.zombie.world.c(this.zombie.getId(), this.blockPosition, breaksit);
			this.breaksit = breaksit;
		}
		if (this.breakTime == 30) {
			if (CraftEventFactory.callEntityChangeBlockEvent(this.zombie, this.blockPosition, this.block, 0)
					.isCancelled()) {
				this.c();
				return;
			}
			this.zombie.world.setAir(this.blockPosition);
			final Zombie z = (Zombie) this.zombie.getBukkitEntity();
			for (ItemStack itemStack : this.itemStacks)
				z.getWorld().dropItem(new Location(z.getWorld(), this.blockPosition.getX(), this.blockPosition.getY(),
						this.blockPosition.getZ()), itemStack);
		}
	}
}