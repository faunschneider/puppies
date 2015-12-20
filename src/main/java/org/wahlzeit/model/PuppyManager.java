package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

public class PuppyManager {

	private final Map<String, PuppyType> types = new HashMap<>();

	public Puppy createPuppy(String typeName) {
		final PuppyType t = getPuppyTypeForName(typeName);
		if (t == null) {
			throw new IllegalArgumentException("No PuppyType with name \"" + typeName + "\" found");
		}
		return t.createInstance();
	}

	private synchronized PuppyType getPuppyTypeForName(String typeName) {
		return types.get(typeName);
	}

	public synchronized void createPuppyType(String typeName, String race, int typicalShoulderHeight, String origin) {
		PuppyType t = new PuppyType(race, typicalShoulderHeight, origin);
		types.put(typeName, t);
	}

}
