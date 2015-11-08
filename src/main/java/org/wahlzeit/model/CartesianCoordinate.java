package org.wahlzeit.model;

import java.io.Serializable;

/**
 * CartesianCoordinate represents a Coordinate in a cartesian coordinate system.
 * The center of the coordinate system is the Earth, where the x-Axis goes through the equator at latitude, longitude
 * (0,0), the y-Axis goes through (0, 90) and the z-Axis goes through the poles.
 */
public class CartesianCoordinate implements Serializable, Coordinate {
	private double x;
	private double y;
	private double z;
	private static final float EQUALS_ALLOWED_DELTA = 0.001f;

	/**
	 * Constructs an empty CartesianCoordinate, i.e. x = y = z = 0.
	 */
	public CartesianCoordinate() {
		this(0.0, 0.0, 0.0);
	}

	/**
	 * Constructs a new CartesianCoordinate.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
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
	public boolean isEqual(Coordinate other) {
		CartesianCoordinate cartesian = CoordinateUtil.coordinateAsCartesianCoordinate(other);
		return Math.abs(x - cartesian.x) <= EQUALS_ALLOWED_DELTA
		       && Math.abs(y - cartesian.y) <= EQUALS_ALLOWED_DELTA
		       && Math.abs(z - cartesian.z) <= EQUALS_ALLOWED_DELTA;
	}

	@Override
	public double getDistance(Coordinate other) {
		CartesianCoordinate cartesian = CoordinateUtil.coordinateAsCartesianCoordinate(other);
		return Math.sqrt(Math.pow(x - cartesian.x, 2) + Math.pow(y - cartesian.y, 2) + Math.pow(z - cartesian.z, 2));
	}

}
