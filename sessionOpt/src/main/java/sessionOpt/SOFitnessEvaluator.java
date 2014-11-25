package sessionOpt;
import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

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
		// TODO Auto-generated method stub
		for (Slot slot: candidate.getSlots()){
			if (slot.getDate().getHours() == 10 && slot.getRoom().getName().equals("New York") && slot.getSession() != null && slot.getSession().getName().equals("Bierbrauen leicht gemacht") ||
				slot.getDate().getHours() == 9 && slot.getRoom().getName().equals("Mainz") && slot.getSession() != null && slot.getSession().getName().equals("Java. Eine Insel...")){
				//Alles gut...
			} else if (slot.getSession() != null){
				result += SMALL_PENALTY;
			}
			//Wir mögen keine leeren Sessions am Tag. Am Ende ists ok..
			if (slot.getSession() == null){
				if (slot.getDate().getHours() != 11){
					result += MINIMAL_PENALTY;
				}
			}
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
