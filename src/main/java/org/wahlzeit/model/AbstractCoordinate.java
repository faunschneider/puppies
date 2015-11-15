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
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate must not be null");
		}
		if (!(coordinate instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("coordinate must subclass AbstractCoordinate");
		}
		return ((AbstractCoordinate) coordinate).asCartesianCoordinate();
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
