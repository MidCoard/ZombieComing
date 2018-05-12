package com.focess.zombiecoming.pathfinder;

public class BlockPosition_1_7 {
	
	public static final BlockPosition_1_7 ZERO = new BlockPosition_1_7(0,0,0);
	private int x;
	private int y;
	private int z;
	
	public BlockPosition_1_7(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

}
