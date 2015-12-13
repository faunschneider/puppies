package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

/**
 * CartesianCoordinate represents a Coordinate in a cartesian coordinate system.
 * The center of the coordinate system is the Earth, where the x-Axis goes through the equator at latitude, longitude
 * (0,0), the y-Axis goes through (0, 90) and the z-Axis goes through the poles.
 */
public class CartesianCoordinate extends AbstractCoordinate {
	private final double x;
	private final double y;
	private final double z;
	private static final float EQUALS_ALLOWED_DELTA = 0.001f;
	private static final Map<CartesianCoordinate, CartesianCoordinate> instances = new HashMap<>();

	/**
	 * Constructs a new CartesianCoordinate.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @precondition x, y, z must not be NaN or infinity.
	 */
	private CartesianCoordinate(double x, double y, double z) {
		assertValidDouble(x);
		assertValidDouble(y);
		assertValidDouble(z);
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	/**
	 * Creates a new CartesianCoordinate or returns a cached one.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @precondition x, y, z must not be NaN or infinity.
	 */
	public static CartesianCoordinate getInstance(double x, double y, double z) {
		final CartesianCoordinate key = new CartesianCoordinate(x, y, z);
		CartesianCoordinate instance = instances.get(key);
		if (instance != null) {
			return instance;
		}
		instances.put(key, key);
		return key;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CartesianCoordinate that = (CartesianCoordinate) o;

		if (Double.compare(that.x, x) != 0) {
			return false;
		}
		if (Double.compare(that.y, y) != 0) {
			return false;
		}
		return Double.compare(that.z, z) == 0;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	protected void assertClassInvariants() {
		assertValidDouble(x);
		assertValidDouble(y);
		assertValidDouble(z);
	}

	@Override
	protected CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * Checks whether this CartesianCoordinate is about the same as another. A little error in each direction is
	 * tolerated.
	 * @param other The CartesianCoordinate to compare to. Must not be null.
	 * @return true if equal, false otherwise.
	 * @methodtype boolean query
	 * @precondition other must not be null.
	 */
	public boolean isCartesianEqual(CartesianCoordinate other) {
		assertNotNull(other);
		return Math.abs(x - other.x) <= EQUALS_ALLOWED_DELTA
		       && Math.abs(y - other.y) <= EQUALS_ALLOWED_DELTA
		       && Math.abs(z - other.z) <= EQUALS_ALLOWED_DELTA;
	}

	/**
	 * Gets the cartesian distance between this CartesianCoordinate and another.
	 * @param other The CartesianCoordinate to calculate the distance to. Must not be null.
	 * @return the distance in meters
	 * @methodtype get
	 * @precondition other must not be null.
	 */
	public double getCartesianDistance(CartesianCoordinate other) {
		assertNotNull(other);
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
	}

}
