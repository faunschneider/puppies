package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

	private static final float EQUALS_FLOAT_DELTA = 0.01f;
	private SphericCoordinate erlangenSchlossSpheric = new SphericCoordinate(49.597891, 11.004623);
	private CartesianCoordinate erlangenSchlossCartesian  = new CartesianCoordinate(4053419.089, 788244.274, 4851608.563);
	private SphericCoordinate roterPlatzSpheric = new SphericCoordinate(49.574661, 11.029198);
	private CartesianCoordinate roterPlatzCartesian  = new CartesianCoordinate(4055010.998, 790359.020, 4849933.961);

	@Test
	public void isEqualSameCoordinate() {
		assertTrue(erlangenSchlossSpheric.isEqual(erlangenSchlossSpheric));
		assertTrue(erlangenSchlossCartesian.isEqual(erlangenSchlossCartesian));
	}

	@Test
	public void isEqualDifferentTypes() {
		assertTrue(erlangenSchlossCartesian.isEqual(erlangenSchlossSpheric));
		assertTrue(erlangenSchlossSpheric.isEqual(erlangenSchlossCartesian));
		assertFalse(erlangenSchlossSpheric.isEqual(new CartesianCoordinate(0, 1, 2)));
	}

	@Test
	public void getDistanceZeroSameCoordinate() {
		assertEquals(0, erlangenSchlossSpheric.getDistance(erlangenSchlossSpheric), EQUALS_FLOAT_DELTA);
		assertEquals(0, erlangenSchlossCartesian.getDistance(erlangenSchlossCartesian), EQUALS_FLOAT_DELTA);
	}

	@Test
	public void getDistanceZeroSameCoordinateDifferentTypes() {
		assertEquals(0, erlangenSchlossSpheric.getDistance(erlangenSchlossCartesian), EQUALS_FLOAT_DELTA);
		assertEquals(0, erlangenSchlossCartesian.getDistance(erlangenSchlossSpheric), EQUALS_FLOAT_DELTA);
	}

	@Test
	public void getDistanceForSameTypes() {
		assertEquals(3132.19, erlangenSchlossSpheric.getDistance(roterPlatzSpheric), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, roterPlatzCartesian.getDistance(erlangenSchlossCartesian), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, erlangenSchlossCartesian.getDistance(roterPlatzCartesian), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, roterPlatzSpheric.getDistance(erlangenSchlossSpheric), EQUALS_FLOAT_DELTA);
	}

	@Test
	public void getDistanceForDifferentTypes() {
		assertEquals(3132.19, erlangenSchlossSpheric.getDistance(roterPlatzCartesian), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, roterPlatzSpheric.getDistance(erlangenSchlossCartesian), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, erlangenSchlossCartesian.getDistance(roterPlatzSpheric), EQUALS_FLOAT_DELTA);
		assertEquals(3132.19, roterPlatzCartesian.getDistance(erlangenSchlossSpheric), EQUALS_FLOAT_DELTA);
	}

}
