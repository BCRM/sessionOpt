package sessionOpt.entities;

public interface Prerequisite {
	public int getUnsatisfiedPenalty();
	public double getHappiness(Feature feature);
	public String getName();
}
