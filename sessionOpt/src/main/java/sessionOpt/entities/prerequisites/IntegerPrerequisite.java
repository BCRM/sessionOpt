package sessionOpt.entities.prerequisites;

import sessionOpt.SOFitnessEvaluator;
import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;
import sessionOpt.entities.features.IntegerFeature;

public class IntegerPrerequisite implements Prerequisite {
	
	private int size;
	private String name;
	
	public IntegerPrerequisite(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public int getHappiness(Feature feature) {
		int result = 0;
		
		IntegerFeature seats = (IntegerFeature)feature;
		if (seats.getSize() < getSize()) {
			result = SOFitnessEvaluator.MEDIUM_PENALTY + getSize() - seats.getSize(); 
		} else {
			result = seats.getSize() - getSize();
		}
		return result;
	}
	@Override
	public int getUnsatisfiedPenalty() {
		return SOFitnessEvaluator.LARGE_PENALTY;
	}

	public String getName() {
		return name;
	}
	
	
}
