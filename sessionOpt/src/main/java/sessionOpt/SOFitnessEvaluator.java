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
		for (Slot slot: candidate.getSlots()) {
			result += slot.getHappiness();
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
