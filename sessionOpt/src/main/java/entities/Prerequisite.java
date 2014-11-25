package entities;

public interface Prerequisite {
	public boolean isSatisfiedBy(Feature feature);
	public String getName();
}
