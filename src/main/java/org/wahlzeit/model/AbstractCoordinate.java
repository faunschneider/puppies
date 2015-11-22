package org.wahlzeit.model;

import java.io.Serializable;

public abstract class AbstractCoordinate implements Coordinate, Serializable {

	/**
	 * Returns the given Coordinate as a CartesianCoordinate by converting it if it is not already a
	 * CartesianCoordinate.
	 * @param coordinate The Coordinate to convert.
	 * @return a CartesianCoordinate, might be the same object as the provided Coordinate.
	 * @methodtype conversion
	 */
	public static CartesianCoordinate coordinateAsCartesianCoordinate(Coordinate coordinate) {
		assertNotNull(coordinate);
		assertIsAbstractCoordinate(coordinate);
		return ((AbstractCoordinate) coordinate).asCartesianCoordinate();
	}

	protected static void assertNotNull(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate must not be null");
		}
	}

	protected static void assertIsAbstractCoordinate(Coordinate coordinate) {
		if (!(coordinate instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("coordinate must subclass AbstractCoordinate");
		}
	}

	protected static void assertValidDouble(double d) {
		if (Double.isNaN(d)) {
			throw new IllegalArgumentException("d must not be NaN");
		}
		if (Double.isInfinite(d)) {
			throw new IllegalArgumentException("d must not be infinite");
		}
	}

	/**
	 * Converts this AbstractCoordinate to a CartesianCoordinate.
	 * @return a CartesianCoordinate. Might be the same as the original instance.
	 * @methodtype conversion
	 */
	protected abstract CartesianCoordinate asCartesianCoordinate();

	@Override
	public boolean isEqual(Coordinate other) {
		return coordinateAsCartesianCoordinate(this).isCartesianEqual(coordinateAsCartesianCoordinate(other));
	}

	@Override
	public double getDistance(Coordinate other) {
		return coordinateAsCartesianCoordinate(this).getCartesianDistance(coordinateAsCartesianCoordinate(other));
	}

}
