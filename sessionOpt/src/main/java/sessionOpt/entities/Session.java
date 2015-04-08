package sessionOpt.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sessionOpt.Penalties;
import sessionOpt.entities.prerequisites.BooleanPrerequisite;
import sessionOpt.entities.prerequisites.IntegerPrerequisite;
import sessionOpt.tools.Tools;


public class Session {
	private String name;
	private List<String> speaker;
	private Map<String, Prerequisite> preRequisites;
	private Date fixedDate;
	
	public Session(String name, List<String> speaker,
			Map<String, Prerequisite> preRequisites){
		this(name, speaker, preRequisites, null);
	}
	
	public Session(String name, List<String> speaker,
			Map<String, Prerequisite> preRequisites, Date fixedDate) {
		super();
		this.name = name;
		this.speaker = speaker;
		this.preRequisites = preRequisites;
		this.fixedDate = fixedDate;
	}
	
	public int getHappiness(Room room, Date date, Penalties pen) {
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
		if (getFixedDate() != null){
			if (!date.equals(getFixedDate())){
				result += pen.getPenaltyFor(Penalties.NOT_MATCHING_FIXED_DATE);
			}
		}
		if (room.isOverflow()){
			result += pen.getPenaltyFor(Penalties.NO_ROOM_FOUND_AT_ALL);
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
		result.append(Tools.appendSpaces(getName() + " -- " + getSpeaker(), 50));
		result.append(" NEED:");
		for (Prerequisite pre: getPreRequisites().values()){
			if (pre instanceof BooleanPrerequisite){
				result.append(pre.getName()+ " ");
			} else if (pre instanceof IntegerPrerequisite){
				result.append(pre.getName() + "(" + ((IntegerPrerequisite)pre).getSize() +") ");
			}
		}
		if (getFixedDate() != null){
			result.append("FD: " + getFixedDate());
		}
		return result.toString();
	}

	public Date getFixedDate() {
		return fixedDate;
	}

	
	
}
