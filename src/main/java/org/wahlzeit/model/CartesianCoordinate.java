package org.wahlzeit.model;

/**
 * CartesianCoordinate represents a Coordinate in a cartesian coordinate system.
 * The center of the coordinate system is the Earth, where the x-Axis goes through the equator at latitude, longitude
 * (0,0), the y-Axis goes through (0, 90) and the z-Axis goes through the poles.
 */
public class CartesianCoordinate extends AbstractCoordinate {
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
	 * @precondition x, y, z must not be NaN or infinity.
	 */
	public CartesianCoordinate(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 * @precondition x must not be NaN or infinity
	 */
	public void setX(double x) {
		assertValidDouble(x);
		this.x = x;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 * @precondition y must not be NaN or infinity
	 */
	public void setY(double y) {
		assertValidDouble(y);
		this.y = y;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 * @precondition z must not be NaN or infinity
	 */
	public void setZ(double z) {
		assertValidDouble(z);
		this.z = z;
		assertClassInvariants();
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
