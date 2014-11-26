package sessionOpt.entities;

import java.util.List;

import sessionOpt.entities.prerequisites.BooleanPrerequisite;


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
		if (getPreRequisites() != null) {
			for (Prerequisite pre: getPreRequisites()) {
				boolean foundFeature = false;
				if (room.getFeatures() != null) {
					for (Feature feat: room.getFeatures()) {
						if (pre.getName().equals(feat.getName())){
							result += pre.getHappiness(feat);
							foundFeature = true;
						}
					}
				}
				
				if (!foundFeature) {
					result += pre.getUnsatisfiedPenalty();
				}
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
		StringBuilder result = new StringBuilder();
		result.append(getName() + " -- " + getSpeaker());
		for (Prerequisite pre: getPreRequisites()){
			if (pre instanceof BooleanPrerequisite){
				result.append("-- NEED:" + pre.getName());
			}
		}
		return result.toString();
	}
	
	
}
