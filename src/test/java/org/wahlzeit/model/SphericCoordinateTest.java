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
		SphericCoordinate.getInstance(-90.0, 0);
		SphericCoordinate.getInstance(90.0, 0);
	}

	@Test
	public void ensureLongitudeValidInBoundaries() {
		SphericCoordinate.getInstance(0, -180.0);
		SphericCoordinate.getInstance(0, 180.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooSmall() {
		SphericCoordinate.getInstance(-90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLatitudeTooBig() {
		SphericCoordinate.getInstance(90.1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooSmall() {
		SphericCoordinate.getInstance(0, -180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorThrowsOnLongitudeTooBig() {
		SphericCoordinate.getInstance(0, 180.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getDistanceThrowsOnNullParameter() {
		SphericCoordinate.getInstance(0.0, 0.0).getDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLatitudinalDistanceThrowsOnNullParameter() {
		SphericCoordinate.getInstance(0.0, 0.0).getLatitudinalDistance(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getLongitudinalDistanceThrowsOnNullParameter() {
		SphericCoordinate.getInstance(0.0, 0.0).getLongitudinalDistance(null);
	}

	@Test
	public void getLatitudinalDistanceBasic() {
		SphericCoordinate a = SphericCoordinate.getInstance(20.0, 30.0);
		SphericCoordinate b = SphericCoordinate.getInstance(50.0, 10.0);
		assertEquals(30.0, a.getLatitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLatitudinalDistanceOrderDoesNotMatter() {
		SphericCoordinate a = SphericCoordinate.getInstance(20.0, 30.0);
		SphericCoordinate b = SphericCoordinate.getInstance(50.0, 10.0);
		assertEquals(a.getLatitudinalDistance(b), b.getLatitudinalDistance(a), 0.0);
	}

	@Test
	public void getLongitudinalDistanceBasic() {
		SphericCoordinate a = SphericCoordinate.getInstance(20.0, 30.0);
		SphericCoordinate b = SphericCoordinate.getInstance(0.0, 10.0);
		assertEquals(20.0, a.getLongitudinalDistance(b), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void getLongitudinalDistanceOrderDoesNotMatter() {
		SphericCoordinate a = SphericCoordinate.getInstance(20.0, 30.0);
		SphericCoordinate b = SphericCoordinate.getInstance(50.0, 10.0);
		assertEquals(a.getLongitudinalDistance(b), b.getLongitudinalDistance(a), 0.0);
	}

	@Test
	public void getSphericalDistanceBasic() {
		SphericCoordinate erlangenSchloss = SphericCoordinate.getInstance(49.597891, 11.004623);
		SphericCoordinate roterPlatz = SphericCoordinate.getInstance(49.574661, 11.029198);
		assertEquals(3132.19, erlangenSchloss.getSphericalDistance(roterPlatz), DOUBLE_EQUALS_DELTA);
		assertEquals(3132.19, roterPlatz.getSphericalDistance(erlangenSchloss), DOUBLE_EQUALS_DELTA);
	}

	@Test
	public void testSharing() {
		SphericCoordinate a = SphericCoordinate.getInstance(10, 50);
		SphericCoordinate b = SphericCoordinate.getInstance(10, 50);
		SphericCoordinate c = SphericCoordinate.getInstance(50, 50);
		assertSame(a, b);
		assertNotSame(a, c);
		assertNotSame(b, c);
	}

}
