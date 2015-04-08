package sessionOpt;

import java.util.Hashtable;
import java.util.Map;

public class Penalties {
	public static final String NO_ROOM_FOUND_AT_ALL = "NO_ROOM_FOUND_AT_ALL";
	public static final String NOT_MATCHING_FIXED_DATE = "NOT_MATCHING_FIXED_DATE";
	public static final String SAME_SPEAKER_TWICE_ON_A_DATE = "SAME_SPEAKER_TWICE_ON_A_DATE";
	
	
	private Map<String, Integer> listOfPenalties = new Hashtable<String, Integer>();
	private int defaultPenalty;


	public Penalties(int defaultPenalty) {
		super();
		this.defaultPenalty = defaultPenalty;
	}
	
	public void setPenalty(String crime, int penalty){
		listOfPenalties.put(crime, penalty);
	}
	
	public int getPenaltyFor(String crime){
		Integer result = listOfPenalties.get(crime);
		if (result == null){
			return defaultPenalty;
		} else {
			return result.intValue();
		}
	}
	
	
}
