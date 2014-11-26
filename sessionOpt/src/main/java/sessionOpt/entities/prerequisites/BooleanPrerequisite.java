package sessionOpt.entities.prerequisites;

import sessionOpt.SOFitnessEvaluator;
import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;

public class BooleanPrerequisite implements Prerequisite {
	
	private String name;
	
	public BooleanPrerequisite(String name) {
		this.name = name;
	}

	@Override
	public int getUnsatisfiedPenalty() {
		return SOFitnessEvaluator.MEDIUM_PENALTY;
	}

	@Override
	public int getHappiness(Feature feature) {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

}
