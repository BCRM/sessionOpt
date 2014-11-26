package sessionOpt.entities.prerequisites;

import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;

public class BooleanPrerequisite implements Prerequisite {
	
	private String name;
	private int penalty;
	
	public BooleanPrerequisite(String name, int unsatisfiedPenalty) {
		this.name = name;
		this.penalty = unsatisfiedPenalty;
	}

	@Override
	public int getUnsatisfiedPenalty() {
		return penalty;
	}

	@Override
	public double getHappiness(Feature feature) {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

}
