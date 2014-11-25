package sessionOpt.entities.prerequisites;

import sessionOpt.entities.Feature;
import sessionOpt.entities.features.Seats;

public class Audience extends IntegerPrerequisite {
	
	public Audience(int size) {
		super(size);
	}

	@Override
	public String getName() {
		return "Audience of " + getSize();
	}

	@Override
	public Class<? extends Feature> getSatisfyingFeature() {
		return Seats.class;
	}
	
	
	@Override
	public int getHappiness(Feature feature) {
		if (feature.getClass().equals(getSatisfyingFeature())) {
			return getSize() - ((Seats)feature).getSize(); 
		}
		
		return 0;
	}
}
