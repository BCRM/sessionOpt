import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import entities.Solution;


public class SOFitnessEvaluator implements FitnessEvaluator<Solution>{

	@Override
	public double getFitness(Solution candidate,
			List<? extends Solution> population) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/**
	 * True = Höhere Werte sind besser, False = Niedriegere sind besser
	 */
	public boolean isNatural() {
		return false;
	}

}
