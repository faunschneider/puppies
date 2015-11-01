package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * Location stores information about the place where a photo was taken.
 * It has a user supplied name and can optionally contain an exact Coordinate.
 */
public class Location extends DataObject {

	private String name;
	private Coordinate coordinate;

	public Location(String name) {
		this(name, null);
	}

	public Location(String name, Coordinate coordinate) {
		this.name = name;
		this.coordinate = coordinate;
		incWriteCount();
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

}
