package sessionOpt.entities;

import java.util.List;
import java.util.Map;

import sessionOpt.entities.prerequisites.BooleanPrerequisite;


public class Session {
	private String name;
	private List<String> speaker;
	private Map<String, Prerequisite> preRequisites;
	
	public Session(String name, List<String> speaker,
			Map<String, Prerequisite> preRequisites) {
		super();
		this.name = name;
		this.speaker = speaker;
		this.preRequisites = preRequisites;
	}
	
	public int getHappiness(Room room) {
		int result = 0;
		if (getPreRequisites() != null) {
			for (Prerequisite pre: getPreRequisites().values()) {
				Feature feat = room.getFeatures().get(pre.getName()); 
				if (feat != null) {
					result += pre.getHappiness(feat);
				} else {
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


	public Map<String, Prerequisite> getPreRequisites() {
		return preRequisites;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getName() + " -- " + getSpeaker());
		for (Prerequisite pre: getPreRequisites().values()){
			if (pre instanceof BooleanPrerequisite){
				result.append(" -- NEED:" + pre.getName());
			}
		}
		return result.toString();
	}
	
	
}
