package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {
	/**
	 * Determines how much a double is allowed to differ from another double
	 * during equality assertions. Background: floating point numbers can not be
	 * represented exactly, so a calculation result might differ slightly from
	 * the correct solution.
	 */
	private static final double DOUBLE_EQUALS_DELTA = 0.001f;

	@Test
	public void ensureLatitudeValidInBoundaries() {
		new Coordinate(-90.0, 0);
		new Coordinate(90.0, 0);
	}

	@Test
	public void ensureLongitudeValidInBoundaries() {
		new Coordinate(0, -180.0);
		new Coordinate(0, 180.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooSmall() {
		new Coordinate(-90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooBig() {
		new Coordinate(90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooSmall() {
		new Coordinate(0, -180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooBig() {
		new Coordinate(0, 180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getDistanceThrowsOnNullParameter() {
		new Coordinate().getDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLatitudinalDistanceThrowsOnNullParameter() {
		new Coordinate().getLatitudinalDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLongitudinalDistanceThrowsOnNullParameter() {
		new Coordinate().getLongitudinalDistance(null);
	}

	@Test
	public void getLatitudinalDistanceBasic() {
		Coordinate a = new Coordinate(20.0, 30.0);
		Coordinate b = new Coordinate(50.0, 10.0);
		assertEquals(30.0, a.getLatitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLatitudinalDistanceOrderDoesNotMatter() {
		Coordinate a = new Coordinate(20.0, 30.0);
		Coordinate b = new Coordinate(50.0, 10.0);
		assertEquals(a.getLatitudinalDistance(b), b.getLatitudinalDistance(a), 0.0);
	}

	@Test
	public void getLongitudinalDistanceBasic() {
		Coordinate a = new Coordinate(20.0, 30.0);
		Coordinate b = new Coordinate(50.0, 10.0);
		assertEquals(20.0, a.getLongitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLongitudinalDistanceOrderDoesNotMatter() {
		Coordinate a = new Coordinate(20.0, 30.0);
		Coordinate b = new Coordinate(50.0, 10.0);
		assertEquals(a.getLongitudinalDistance(b), b.getLongitudinalDistance(a), 0.0);
	}

	@Test
	public void getDistanceBasic() {
		Coordinate erlangenSchloss = new Coordinate(49.597891, 11.004623);
		Coordinate roterPlatz = new Coordinate(49.574661, 11.029198);
		assertEquals(3132.19, erlangenSchloss.getDistance(roterPlatz), DOUBLE_EQUALS_DELTA);
		assertEquals(3132.19, roterPlatz.getDistance(erlangenSchloss), DOUBLE_EQUALS_DELTA);
	}

}
