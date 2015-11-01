package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * A PuppyPhoto is a subclass of Photo which provides additional information about puppies.
 * A puppy has a name and belongs to a race. It has an age and weight attribute, as well as one for the shoulder height.
 */
// By using the @Subclass annotation we make sure that we can load and store a PuppyPhoto everywhere where we usually
// use a Photo.
@Subclass
public class PuppyPhoto extends Photo {

	private String name;
	private String race;
	private int age;
	private int shoulderHeight;
	private float weight;

	public PuppyPhoto() {
		super();
	}

	public PuppyPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodtype get
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @methodtype set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @methodtype get
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @methodtype set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @methodtype get
	 */
	public int getShoulderHeight() {
		return shoulderHeight;
	}

	/**
	 * @methodtype set
	 */
	public void setShoulderHeight(int shoulderHeight) {
		this.shoulderHeight = shoulderHeight;
	}

	/**
	 * @methodtype get
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @methodtype set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
}
