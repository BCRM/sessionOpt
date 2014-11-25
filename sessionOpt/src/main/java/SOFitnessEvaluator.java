import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import entities.Slot;
import entities.Solution;


public class SOFitnessEvaluator implements FitnessEvaluator<Solution>{

	@Override
	public double getFitness(Solution candidate,
			List<? extends Solution> population) {
		double result = 0;
		// TODO Auto-generated method stub
		for (Slot slot: candidate.getSlots()){
			if (slot.getRoom().getName().equals("Mainz") && slot.getSession() != null && slot.getSession().getName().equals("Bierbrauen leicht gemacht")){
				//Alles gut...
			} else if (slot.getSession() != null){
				result += 1;
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
