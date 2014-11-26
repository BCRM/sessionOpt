package sessionOpt;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import sessionOpt.entities.Room;
import sessionOpt.entities.Slot;
import sessionOpt.entities.Solution;



public class SOFitnessEvaluator implements FitnessEvaluator<Solution>{
	public static int MINIMAL_PENALTY = 1;
	public static int SMALL_PENALTY = 10;
	public static int MEDIUM_PENALTY = 100;
	public static int LARGE_PENALTY = 1000;
	public static int HORRENDOUS_PENALTY = 1000000;

	@Override
	public double getFitness(Solution candidate,
			List<? extends Solution> population) {
		double result = 0;
		//Bedingungen der Slots
		for (Slot slot: candidate.getSlots()) {
			result += slot.getHappiness();
		}
		//Generelle Bedingungen
		Set<String> speakers = new HashSet<String>();
		for (Date date: candidate.getDates()){
			Map<Room, Slot> slots = candidate.getSlotsByDate(date);
			for (Slot slot: slots.values()){
				if (slot.getSession() != null){
					for (String speaker: slot.getSession().getSpeaker()){
						if (!speakers.add(speaker)){
							//Ah shit. Gleicher Speaker am gleichen Tag. No go!
							result += HORRENDOUS_PENALTY;
						}
					}
				}
			}
			speakers.clear();
		}
		return result;
	}

	@Override
	/**
	 * True = Höhere Werte sind besser, False = Niedriegere sind besser
	 */
	public boolean isNatural() {
		return false;
	}

}
