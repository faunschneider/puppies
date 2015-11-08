package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class SphericCoordinateTest {
	/**
	 * Determines how much a double is allowed to differ from another double
	 * during equality assertions. Background: floating point numbers can not be
	 * represented exactly, so a calculation result might differ slightly from
	 * the correct solution.
	 */
	private static final double DOUBLE_EQUALS_DELTA = 0.001f;

	@Test
	public void ensureLatitudeValidInBoundaries() {
		new SphericCoordinate(-90.0, 0);
		new SphericCoordinate(90.0, 0);
	}

	@Test
	public void ensureLongitudeValidInBoundaries() {
		new SphericCoordinate(0, -180.0);
		new SphericCoordinate(0, 180.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooSmall() {
		new SphericCoordinate(-90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooBig() {
		new SphericCoordinate(90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooSmall() {
		new SphericCoordinate(0, -180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooBig() {
		new SphericCoordinate(0, 180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getDistanceThrowsOnNullParameter() {
		new SphericCoordinate().getDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLatitudinalDistanceThrowsOnNullParameter() {
		new SphericCoordinate().getLatitudinalDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLongitudinalDistanceThrowsOnNullParameter() {
		new SphericCoordinate().getLongitudinalDistance(null);
	}

	@Test
	public void getLatitudinalDistanceBasic() {
		SphericCoordinate a = new SphericCoordinate(20.0, 30.0);
		SphericCoordinate b = new SphericCoordinate(50.0, 10.0);
		assertEquals(30.0, a.getLatitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLatitudinalDistanceOrderDoesNotMatter() {
		SphericCoordinate a = new SphericCoordinate(20.0, 30.0);
		SphericCoordinate b = new SphericCoordinate(50.0, 10.0);
		assertEquals(a.getLatitudinalDistance(b), b.getLatitudinalDistance(a), 0.0);
	}

	@Test
	public void getLongitudinalDistanceBasic() {
		SphericCoordinate a = new SphericCoordinate(20.0, 30.0);
		SphericCoordinate b = new SphericCoordinate(50.0, 10.0);
		assertEquals(20.0, a.getLongitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLongitudinalDistanceOrderDoesNotMatter() {
		SphericCoordinate a = new SphericCoordinate(20.0, 30.0);
		SphericCoordinate b = new SphericCoordinate(50.0, 10.0);
		assertEquals(a.getLongitudinalDistance(b), b.getLongitudinalDistance(a), 0.0);
	}

	@Test
	public void getSphericalDistanceBasic() {
		SphericCoordinate erlangenSchloss = new SphericCoordinate(49.597891, 11.004623);
		SphericCoordinate roterPlatz = new SphericCoordinate(49.574661, 11.029198);
		assertEquals(3132.19, erlangenSchloss.getSphericalDistance(roterPlatz), DOUBLE_EQUALS_DELTA);
		assertEquals(3132.19, roterPlatz.getSphericalDistance(erlangenSchloss), DOUBLE_EQUALS_DELTA);
	}

}
