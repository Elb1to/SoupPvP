package me.elb1to.souppvp.utils.cuboid;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by:
 * @Tristiisch74
 */
public class Cuboid {

	private final int xMin;
	private final int xMax;
	private final int yMin;
	private final int yMax;
	private final int zMin;
	private final int zMax;

	private final double xMinCentered;
	private final double xMaxCentered;
	private final double yMinCentered;
	private final double yMaxCentered;
	private final double zMinCentered;
	private final double zMaxCentered;

	private final World world;

	public Cuboid(final Location point1, final Location point2) {
		this.xMin = Math.min(point1.getBlockX(), point2.getBlockX());
		this.xMax = Math.max(point1.getBlockX(), point2.getBlockX());
		this.yMin = Math.min(point1.getBlockY(), point2.getBlockY());
		this.yMax = Math.max(point1.getBlockY(), point2.getBlockY());
		this.zMin = Math.min(point1.getBlockZ(), point2.getBlockZ());
		this.zMax = Math.max(point1.getBlockZ(), point2.getBlockZ());
		this.world = point1.getWorld();
		this.xMinCentered = this.xMin + 0.5;
		this.xMaxCentered = this.xMax + 0.5;
		this.yMinCentered = this.yMin + 0.5;
		this.yMaxCentered = this.yMax + 0.5;
		this.zMinCentered = this.zMin + 0.5;
		this.zMaxCentered = this.zMax + 0.5;
	}

	/*public Iterator<Block> blockList() {
		final ArrayList<Block> bL = new ArrayList<>(this.getTotalBlockSize());
		for (int x = this.xMin; x <= this.xMax; ++x) {
			for (int y = this.yMin; y <= this.yMax; ++y) {
				for (int z = this.zMin; z <= this.zMax; ++z) {
					final Block b = this.world.getBlockAt(x, y, z);
					bL.add(b).iterator();
				}
			}
		}

		return bL;
	}*/

	public Location getCenter() {
		return new Location(this.world, (this.xMax - this.xMin) / 2 + this.xMin, (this.yMax - this.yMin) / 2 + this.yMin, (this.zMax - this.zMin) / 2 + this.zMin);
	}

	public double getDistance() {
		return this.getPoint1().distance(this.getPoint2());
	}

	public double getDistanceSquared() {
		return this.getPoint1().distanceSquared(this.getPoint2());
	}

	public int getHeight() {
		return this.yMax - this.yMin + 1;
	}

	public Location getPoint1() {
		return new Location(this.world, this.xMin, this.yMin, this.zMin);
	}

	public Location getPoint2() {
		return new Location(this.world, this.xMax, this.yMax, this.zMax);
	}

	public Location getRandomLocation() {
		final Random rand = new Random();
		final int x = rand.nextInt(Math.abs(this.xMax - this.xMin) + 1) + this.xMin;
		final int y = rand.nextInt(Math.abs(this.yMax - this.yMin) + 1) + this.yMin;
		final int z = rand.nextInt(Math.abs(this.zMax - this.zMin) + 1) + this.zMin;

		return new Location(this.world, x, y, z);
	}

	public int getTotalBlockSize() {
		return this.getHeight() * this.getXWidth() * this.getZWidth();
	}

	public int getXWidth() {
		return this.xMax - this.xMin + 1;
	}

	public int getZWidth() {
		return this.zMax - this.zMin + 1;
	}

	public boolean isIn(final Location loc) {
		return loc.getWorld() == this.world
				&& loc.getBlockX() >= this.xMin
				&& loc.getBlockX() <= this.xMax
				&& loc.getBlockY() >= this.yMin
				&& loc.getBlockY() <= this.yMax
				&& loc.getBlockZ() >= this.zMin
				&& loc.getBlockZ() <= this.zMax
				;
	}

	public boolean isIn(final Player player) {
		return this.isIn(player.getLocation());
	}

	public boolean isInWithMarge(final Location loc, final double marge) {
		return loc.getWorld() == this.world
				&& loc.getX() >= this.xMinCentered - marge
				&& loc.getX() <= this.xMaxCentered + marge
				&& loc.getY() >= this.yMinCentered - marge
				&& loc.getY() <= this.yMaxCentered + marge
				&& loc.getZ() >= this.zMinCentered - marge
				&& loc.getZ() <= this.zMaxCentered + marge
				;
	}
}
