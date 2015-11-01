package org.wahlzeit.model;

import java.io.Serializable;

/**
 * A Coordinate represents a tuple of latitude and longitude coordinates.
 */
public class Coordinate implements Serializable {

	private static final int EARTH_RADIUS_IN_METERS = 6371000;

	private double latitude;
	private double longitude;

	/**
	 * Construct an empty Coordinate, with Latitude = Longitude = 0.
	 */
	public Coordinate() {
		latitude = 0.0;
		longitude = 0.0;
	}

	/**
	 * Construct a new Coordinate.
	 * @param latitude The latitude to use.
	 * @param longitude The longitude to use.
	 */
	public Coordinate(double latitude, double longitude) {
		// Use the setters instead of assigning the variables directly
		// to make sure they are validated.
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * Gets the latitude.
	 * @return the latitude coordinate
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * @param newLatitude The new latitude.
	 */
	public void setLatitude(double newLatitude) {
		if (newLatitude < -90.0f || newLatitude > 90.0f) {
			throw new IllegalArgumentException("Latitude must be in range -90.0 - 90.0");
		}
		latitude = newLatitude;
	}

	/**
	 * Gets the longitude.
	 * @return the longitude coordinate
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * @param newLongitude The new longitude.
	 */
	public void setLongitude(double newLongitude) {
		if (newLongitude < -180.0f || newLongitude > 180.0f) {
			throw new IllegalArgumentException("Longitude must be in range -180.0 - 180.0");
		}
		longitude = newLongitude;
	}

	/**
	 * Calculates the latitudinal distance between this Coordinate and an {}@code other}.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the latitudinal distance
	 */
	public double getLatitudinalDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other Coordinate must not be null!");
		}
		return Math.abs(latitude - other.getLatitude());
	}

	/**
	 * Calculates the longitudinal distance between this Coordinate and an {}@code other}.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the longitudinal distance
	 */
	public double getLongitudinalDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other Coordinate must not be null!");
		}
		return Math.abs(longitude - other.getLongitude());
	}

	/**
	 * Calculates the distance in meters between this Coordinate and an {@code other}.
	 * @param other The Coordinate to calculate the distance to.
	 * @return the distance in meters
	 */
	public double getDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other Coordinate must not be null!");
		}

		double phi1 = degreesToRadians(latitude);
		double lambda1 = degreesToRadians(longitude);
		double phi2 = degreesToRadians(other.latitude);
		double lambda2 = degreesToRadians(other.longitude);
		double dphi = phi2 - phi1;
		double dlambda = lambda2 - lambda1;

		double haversin = Math.pow(Math.sin(dphi/2), 2) +
		                  Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(dlambda/2), 2);
		return 2 * EARTH_RADIUS_IN_METERS * Math.asin(Math.sqrt(haversin));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Coordinate that = (Coordinate) o;

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

	/**
	 * Converts degrees to radians.
	 * @param degrees The degrees to convert.
	 * @return radians
	 */
	private static double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180;
	}


}
