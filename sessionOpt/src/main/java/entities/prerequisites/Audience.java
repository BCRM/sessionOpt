package entities.prerequisites;

import entities.Feature;
import entities.features.Seats;

public class Audience extends IntegerPrerequisite {
	
	public Audience(int size) {
		super(size);
	}

	@Override
	public boolean isSatisfiedBy(Feature feature) {
		return feature.getClass().equals(Seats.class);
	}

	@Override
	public String getName() {
		return "Audience of " + getSize();
	}
}
