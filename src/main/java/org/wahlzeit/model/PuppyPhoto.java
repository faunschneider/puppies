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

	private Puppy puppy;

	public PuppyPhoto() {
		super();
	}

	public PuppyPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype get
	 */
	public Puppy getPuppy() {
		return puppy;
	}

	/**
	 * @methodtype set
	 */
	public void setPuppy(Puppy puppy) {
		this.puppy = puppy;
	}

}
