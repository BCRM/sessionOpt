package sessionOpt.entities.prerequisites;

import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;
import sessionOpt.entities.features.IntegerFeature;

public class IntegerPrerequisite implements Prerequisite {
	
	private int size;
	private String name;
	
	private int unsatisfiedPenalty;
	private int notQuietsatisfiedPenalty;
	private double differencePenaltyMod;
	private double positiveDifferencePenaltyMod;
	
	public IntegerPrerequisite(String name, int size, int unsatisfiedPenalty, int notQuietSatisfiedPenalty, double differencePenaltyMod, double positiveDifferenceMod) {
		this.name = name;
		this.size = size;
		this.unsatisfiedPenalty = unsatisfiedPenalty;
		this.notQuietsatisfiedPenalty = notQuietSatisfiedPenalty;
		this.differencePenaltyMod = differencePenaltyMod;
		this.positiveDifferencePenaltyMod = positiveDifferenceMod;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public double getHappiness(Feature feature) {
		double result = 0;
		
		IntegerFeature seats = (IntegerFeature)feature;
		if (seats.getSize() < getSize()) {
			result = notQuietsatisfiedPenalty + (getSize() - seats.getSize()) * differencePenaltyMod; 
		} else {
			result = (seats.getSize() - getSize()) * positiveDifferencePenaltyMod;
		}
		return result;
	}
	@Override
	public int getUnsatisfiedPenalty() {
		return unsatisfiedPenalty;
	}

	public String getName() {
		return name;
	}
	
	
}
