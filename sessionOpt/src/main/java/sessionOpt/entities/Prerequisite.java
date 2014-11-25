package sessionOpt.entities;

public interface Prerequisite {
	public Class<? extends Feature> getSatisfyingFeature();
	public int getHappiness(Feature feature);
	public String getName();
}
