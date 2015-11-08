package org.wahlzeit.model;

public class CoordinateUtil {

	/**
	 * Returns the given Coordinate as a CartesianCoordinate by converting it if it is not already a
	 * CartesianCoordinate.
	 * @param coordinate The Coordinate to convert.
	 * @return a CartesianCoordinate, might be the same object as the provided Coordinate.
	 * @methodtype conversion
	 */
	public static CartesianCoordinate coordinateAsCartesianCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate must not be null");
		}
		if (coordinate instanceof CartesianCoordinate) {
			return (CartesianCoordinate) coordinate;
		}
		if (coordinate instanceof SphericCoordinate) {
			return sphericalAsCartesianCoordinate((SphericCoordinate) coordinate);
		}
		throw new IllegalArgumentException("coordinate must be of type CartesianCoordinate or SphericCoordinate");
	}

	/**
	 * Converts a SphericCoordinate to a CartesianCoordinate.
	 * @param spheric The SphericCoordinate to convert.
	 * @return a new CartesianCoordinate.
	 * @methodtype conversion
	 */
	private static CartesianCoordinate sphericalAsCartesianCoordinate(SphericCoordinate spheric) {
		final double latitudeRadians = Math.toRadians(spheric.getLatitude());
		final double longitudeRadians = Math.toRadians(spheric.getLongitude());
		double x = spheric.getRadius() * Math.cos(latitudeRadians) * Math.cos(longitudeRadians);
		double y = spheric.getRadius() * Math.cos(latitudeRadians) * Math.sin(longitudeRadians);
		double z = spheric.getRadius() * Math.sin(latitudeRadians);
		return new CartesianCoordinate(x, y, z);
	}

}
