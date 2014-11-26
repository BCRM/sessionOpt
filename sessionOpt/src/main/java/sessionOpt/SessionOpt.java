package sessionOpt;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.Stagnation;

import sessionOpt.entities.Solution;
import sessionOpt.operators.MutateOperator;



public class SessionOpt {

	public static void main(String[] args) {
		
		//Preparation
		CandidateFactory<Solution> candidateFactory = new SOCandidateFactory(DummyDataCreator.createDummyRooms(), DummyDataCreator.createDummySessions(), DummyDataCreator.createDummyStartDates());
		
		List<EvolutionaryOperator<Solution>> operators = new LinkedList<EvolutionaryOperator<Solution>>();
//		operators.add(new CrossoverOperator()); //TODO
		operators.add(new MutateOperator());
		
		EvolutionaryOperator<Solution> evolutionaryOperator = new EvolutionPipeline<Solution>(operators);
		FitnessEvaluator<Solution> fitnessEvaluator = new SOFitnessEvaluator();
		RouletteWheelSelection selectionStrategy = new RouletteWheelSelection();
		Random rng = new org.uncommons.maths.random.MersenneTwisterRNG();
		
		//Creation of the engine
		EvolutionEngine<Solution> engine = new GenerationalEvolutionEngine<Solution>(candidateFactory,evolutionaryOperator,fitnessEvaluator,selectionStrategy,rng);

		//Debugging
		engine.addEvolutionObserver(new EvolutionObserver<Solution>()
		{
			private double lastValue = -1;
		    public void populationUpdate(PopulationData<? extends Solution> data)
		    {
		    	if (data.getBestCandidateFitness() != lastValue){
			        System.out.printf("Generation %d: %s\n",
			                          data.getGenerationNumber(),
			                          data.getBestCandidateFitness());
			        lastValue = data.getBestCandidateFitness();
		    	}
		    }
		});

		//Finding the result
		long start = System.currentTimeMillis();
		Solution result = engine.evolve(50, 1, new Stagnation(10, fitnessEvaluator.isNatural()));
		System.out.println("Calculation took " + (System.currentTimeMillis() - start) + "ms.");
		System.out.println(result);
	}
}
