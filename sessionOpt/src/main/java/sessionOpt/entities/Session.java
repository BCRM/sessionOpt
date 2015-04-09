package sessionOpt.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sessionOpt.Penalties;
import sessionOpt.entities.prerequisites.BooleanPrerequisite;
import sessionOpt.entities.prerequisites.IntegerPrerequisite;
import sessionOpt.tools.Tools;
import sessionOpt.tools.WishDates;


public class Session {
	private String name;
	private List<String> speaker;
	private Map<String, Prerequisite> preRequisites;
	private Date fixedDate;
	private WishDates wishDates;
	
	
	public Session(String name, List<String> speaker,
			Map<String, Prerequisite> preRequisites){
		this(name, speaker, preRequisites, null, null);
	}
	
	public Session(String name, List<String> speaker,
			Map<String, Prerequisite> preRequisites, Date fixedDate, WishDates wishDates) {
		super();
		this.name = name;
		this.speaker = speaker;
		this.preRequisites = preRequisites;
		this.fixedDate = fixedDate;
		this.wishDates = wishDates;
	}
	
	/**
	 * Returns the penalties for this Session in regards to the room and date it got assigned
	 * @param room
	 * @param date
	 * @param pen
	 * @return Penalty points for this session in this room at this date given the penalties...
	 */
	public int getHappiness(Room room, Date date, Penalties pen) {
		int result = 0;
		//Erst mal die generischen Vorbedingungen durchgehen
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

		//Ist mein Zeitpunkt gesetzt?
		if (getFixedDate() != null){
			if (!date.equals(getFixedDate())){
				result += pen.getPenaltyFor(Penalties.NOT_MATCHING_FIXED_DATE);
			}
		} else {
			//Kein Fixdatum, aber habe ich Wunschdaten?
			if (getWishDates() != null){
				if (!getWishDates().getDates().contains(date)){
					result += getWishDates().getPenalty();
				}
			}
		}
		
		//Habe ich vielleicht keinen Raum bekommen?
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
			result.append("FD: " + getFixedDate().getHours());
		}
		if (getWishDates() != null){
			result.append("WD: (" + getWishDates().getPenalty() + ") - ");
			for (Date date: getWishDates().getDates()){
				result.append(date.getHours() + " ");
			}
		}

		return result.toString();
	}

	public Date getFixedDate() {
		return fixedDate;
	}

	public WishDates getWishDates() {
		return wishDates;
	}

	
	
}
