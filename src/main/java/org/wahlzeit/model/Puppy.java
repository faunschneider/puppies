package org.wahlzeit.model;

import java.util.Collection;
import java.util.LinkedList;

public class Puppy {

	private final PuppyType type;
	private String name;
	private int age;
	private int shoulderHeight;
	private float weight;
	private final Collection<PuppyPhoto> photos = new LinkedList<>();

	public Puppy(PuppyType type) {
		this.type = type;
	}

	public Puppy(PuppyType type, String name, int age, int shoulderHeight, float weight) {
		this.type = type;
		this.name = name;
		this.age = age;
		this.shoulderHeight = shoulderHeight;
		this.weight = weight;
	}

	public String getRace() {
		return type.getRace();
	}

	public int getTypicalShoulderHeight() {
		return type.getTypicalShoulderHeight();
	}

	public String getOrigin() {
		return type.getOrigin();
	}

	public Collection<PuppyPhoto> getPhotos() {
		return photos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getShoulderHeight() {
		return shoulderHeight;
	}

	public void setShoulderHeight(int shoulderHeight) {
		this.shoulderHeight = shoulderHeight;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
