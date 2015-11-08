package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CoordinateUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void coordinateAsCartesianCoordinateThrowsOnNull() throws Exception {
		CoordinateUtil.coordinateAsCartesianCoordinate(null);
	}

	@Test
	public void coordinateAsCartesianCoordinateReturnsEqualOnCartesian() throws Exception {
		CartesianCoordinate expectedCartesian = new CartesianCoordinate(4053419.089, 788244.274, 4851608.563);
		assertTrue(expectedCartesian.isEqual(CoordinateUtil.coordinateAsCartesianCoordinate(expectedCartesian)));
	}

	@Test
	public void coordinateAsCartesianCoordinateConvertsSpheric() throws Exception {
		SphericCoordinate erlangenSchlossSpheric = new SphericCoordinate(49.597891, 11.004623);
		CartesianCoordinate expectedCartesian = new CartesianCoordinate(4053419.089, 788244.274, 4851608.563);
		assertTrue(expectedCartesian.isEqual(CoordinateUtil.coordinateAsCartesianCoordinate(erlangenSchlossSpheric)));
	}

}
