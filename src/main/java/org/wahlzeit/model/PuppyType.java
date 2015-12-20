package org.wahlzeit.model;

import java.util.Collection;
import java.util.LinkedList;

public class PuppyType {

	private final String race;
	private final int typicalShoulderHeight;
	private final String origin;
	private final Collection<Puppy> instances = new LinkedList<>();

	public PuppyType(String race, int typicalShoulderHeight, String origin) {
		this.race = race;
		this.typicalShoulderHeight = typicalShoulderHeight;
		this.origin = origin;
	}

	public String getRace() {
		return race;
	}

	public int getTypicalShoulderHeight() {
		return typicalShoulderHeight;
	}

	public String getOrigin() {
		return origin;
	}

	public Puppy createInstance() {
		final Puppy instance = new Puppy(this);
		instances.add(instance);
		return instance;
	}

	public Collection<Puppy> getInstances() {
		return instances;
	}

}
