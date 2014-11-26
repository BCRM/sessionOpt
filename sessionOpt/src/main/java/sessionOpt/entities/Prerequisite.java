package sessionOpt.entities;

public interface Prerequisite {
	public int getUnsatisfiedPenalty();
	public int getHappiness(Feature feature);
	public String getName();
}
