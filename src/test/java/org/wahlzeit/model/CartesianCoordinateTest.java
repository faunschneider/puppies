package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	@Test
	public void testSharing() {
		CartesianCoordinate a = CartesianCoordinate.getInstance(10, 50, 10);
		CartesianCoordinate b = CartesianCoordinate.getInstance(10, 50, 10);
		CartesianCoordinate c = CartesianCoordinate.getInstance(50, 50, 50);
		assertSame(a, b);
		assertNotSame(a, c);
		assertNotSame(b, c);
	}

}