package org.wahlzeit.model;

/**
 * A Coordinate represents a tuple of latitude and longitude coordinates on a sphere with a specific radius.
 * The center of the coordinate system is the Earth.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final int EARTH_RADIUS_IN_METERS = 6371000;

	private final double latitude;
	private final double longitude;
	private final double radius;

	/**
	 * Construct an empty Coordinate, with Latitude = Longitude = Radius = 0.
	 */
	public SphericCoordinate() {
		this(0.0, 0.0, 0.0);
	}

	/**
	 * Construct a new Coordinate using the earth radius.
	 * @param latitude The latitude to use.
	 * @param longitude The longitude to use.
	 */
	public SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTH_RADIUS_IN_METERS);
	}

	/**
	 * Construct a new Coordinate.
	 * @param latitude The latitude to use.
	 * @param longitude The longitude to use.
	 * @param radius The radius to use.
	 * @precondition latitude, longitude, radius must not be NaN or infinity.
	 * @precondition latitude must be in range [-90, 90]
	 * @precondition longitude must be in range [-180, 180]
	 * @precondition radius must be in range [0, infinity[
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		assertValidDouble(latitude);
		assertValidDouble(longitude);
		assertValidDouble(radius);
		assertLatitudeInRange(latitude);
		assertLongitudeInRange(longitude);
		assertRadiusIsPositive(radius);

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

		assertClassInvariants();
	}

	/**
	 * Gets the latitude.
	 * @return the latitude coordinate
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Gets the longitude.
	 * @return the longitude coordinate
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Gets the radius.
	 * @return the radius
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Calculates the latitudinal distance between this Coordinate and an {}@code other}.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the latitudinal distance
	 * @methodtype get
	 * @precondition other must not be null.
	 */
	public double getLatitudinalDistance(SphericCoordinate other) {
		assertNotNull(other);
		return Math.abs(latitude - other.getLatitude());
	}

	/**
	 * Calculates the longitudinal distance between this Coordinate and an {}@code other}.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the longitudinal distance
	 * @methodtype get
	 * @precondition other must not be null.
	 */
	public double getLongitudinalDistance(SphericCoordinate other) {
		assertNotNull(other);
		return Math.abs(longitude - other.getLongitude());
	}

	/**
	 * Calculates the distance in meters between this SphericCoordinate and an {@code other}.
	 * Both must be on the same sphere, i.e. their radius must be the same.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the distance in meters
	 * @methodtype get
	 * @precondition other must not be null.
	 * @precondition other must have the same radius as this SphericCoordinate.
	 */
	public double getSphericalDistance(SphericCoordinate other) {
		assertNotNull(other);
		if (radius != other.radius) {
			throw new IllegalArgumentException("other Coordinate is not on the same sphere: [my radius: " + radius
			                                   + ", other radius: " + other.radius + "]");
		}

		double phi1 = Math.toRadians(latitude);
		double lambda1 = Math.toRadians(longitude);
		double phi2 = Math.toRadians(other.latitude);
		double lambda2 = Math.toRadians(other.longitude);
		double dphi = phi2 - phi1;
		double dlambda = lambda2 - lambda1;

		double haversin = Math.pow(Math.sin(dphi/2), 2) +
		                  Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(dlambda/2), 2);
		return 2 * radius * Math.asin(Math.sqrt(haversin));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SphericCoordinate that = (SphericCoordinate) o;

		if (Double.compare(that.latitude, latitude) != 0) {
			return false;
		}
		return Double.compare(that.longitude, longitude) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	private static void assertLatitudeInRange(double latitude) {
		if (latitude < -90.0f || latitude > 90.0f) {
			throw new IllegalArgumentException("Latitude must be in range -90.0 - 90.0");
		}
	}

	private static void assertLongitudeInRange(double longitude) {
		if (longitude < -180.0f || longitude > 180.0f) {
			throw new IllegalArgumentException("Longitude must be in range -180.0 - 180.0");
		}
	}

	private static void assertRadiusIsPositive(double radius) {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must be >= 0");
		}
	}

	@Override
	protected void assertClassInvariants() {
		assertValidDouble(latitude);
		assertValidDouble(longitude);
		assertValidDouble(radius);
		assertLatitudeInRange(latitude);
		assertLongitudeInRange(longitude);
		assertRadiusIsPositive(radius);
	}

	@Override
	protected CartesianCoordinate asCartesianCoordinate() {
		final double latitudeRadians = Math.toRadians(latitude);
		final double longitudeRadians = Math.toRadians(longitude);
		double x = radius * Math.cos(latitudeRadians) * Math.cos(longitudeRadians);
		double y = radius * Math.cos(latitudeRadians) * Math.sin(longitudeRadians);
		double z = radius * Math.sin(latitudeRadians);
		return new CartesianCoordinate(x, y, z);
	}
}
