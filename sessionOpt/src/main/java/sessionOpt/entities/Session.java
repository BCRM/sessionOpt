package sessionOpt.entities;

import java.util.List;

import sessionOpt.entities.Prerequisite;


public class Session {
	private String name;
	private List<String> speaker;
	private List<Prerequisite> preRequisites;
	
	public Session(String name, List<String> speaker,
			List<Prerequisite> preRequisites) {
		super();
		this.name = name;
		this.speaker = speaker;
		this.preRequisites = preRequisites;
	}
	
	public int getHappiness(Room room) {
		int result = 0;
		for (Prerequisite pre: getPreRequisites()) {
			boolean foundFeature = false;
			for (Feature feat: room.getFeatures()) {
				if (pre.getSatisfyingFeature().equals(feat.getClass())) {
					result += pre.getHappiness(feat);
				}
			}
			if (!foundFeature) {
				result += 1000;
			}
		}
		return result;
	}

	public String getName() {
		return name;
	}


	public List<String> getSpeaker() {
		return speaker;
	}


	public List<Prerequisite> getPreRequisites() {
		return preRequisites;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
