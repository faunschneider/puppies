package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AbstractCoordinateTest {

	@Test(expected = IllegalArgumentException.class)
	public void coordinateAsCartesianCoordinateThrowsOnNull() throws Exception {
		AbstractCoordinate.coordinateAsCartesianCoordinate(null);
	}

	@Test
	public void coordinateAsCartesianCoordinateReturnsEqualOnCartesian() throws Exception {
		CartesianCoordinate expectedCartesian = CartesianCoordinate.getInstance(4053419.089, 788244.274, 4851608.563);
		assertTrue(expectedCartesian.isEqual(AbstractCoordinate.coordinateAsCartesianCoordinate(expectedCartesian)));
	}

	@Test
	public void coordinateAsCartesianCoordinateConvertsSpheric() throws Exception {
		SphericCoordinate erlangenSchlossSpheric = SphericCoordinate.getInstance(49.597891, 11.004623);
		CartesianCoordinate expectedCartesian = CartesianCoordinate.getInstance(4053419.089, 788244.274, 4851608.563);
		assertTrue(expectedCartesian.isEqual(
				AbstractCoordinate.coordinateAsCartesianCoordinate(erlangenSchlossSpheric)));
	}

}
