package sessionOpt.entities.prerequisites;

import sessionOpt.SOFitnessEvaluator;
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
		int result = 0;
		
		if (feature.getClass().equals(getSatisfyingFeature())) {
			Seats seats = (Seats)feature;
			if (seats.getSize() < getSize()) {
				result = SOFitnessEvaluator.MEDIUM_PENALTY + getSize() - seats.getSize(); 
			} else {
				result = seats.getSize() - getSize();
			}

		}
		
		return result;
	}

	@Override
	public int getUnsatisfiedPenalty() {
		return SOFitnessEvaluator.LARGE_PENALTY;
	}
}
