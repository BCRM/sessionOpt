package entities.features;

import entities.Prerequisite;
import entities.prerequisites.Audience;

public class Seats extends IntegerFeature {
	
	public Seats(int size) {
		super(size);
	}

	@Override
	public boolean fullfills(Prerequisite req) {
		return req.getClass().equals(Audience.class);
	}

	@Override
	public String getName() {
		return "Seats for " + getSize() + " people";
	}
}
