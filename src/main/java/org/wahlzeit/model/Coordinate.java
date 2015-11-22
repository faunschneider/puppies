package org.wahlzeit.model;

public interface Coordinate {
	/**
	 * Checks if this Coordinate is equal to an {@code other}. This is different than the equals() method in the sense
	 * that this method accounts for a small delta between the two coordinates, whereas equals() checks for the exact
	 * same Coordinate.
	 * @param other The other Coordinate.
	 * @return true if equal, false otherwise.
	 * @methodtype boolean query
	 * @precondition other must not be null.
	 */
	boolean isEqual(Coordinate other);

	/**
	 * Gets the shortest distance between this Coordinate ond an {@code other} in meters.
	 * This is the distance that equals the length of a straight line between the two Coordinates.
	 * @param other The coordinate to calculate the distance to.
	 * @return the distance.
	 * @methodtype get
	 * @precondition other must not be null.
	 */
	double getDistance(Coordinate other);
}
