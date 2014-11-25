import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import operators.MutateOperator;

import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import entities.Room;
import entities.Session;
import entities.Solution;


public class SessionOpt {
	public static void main(String[] args) {
		
		//Preparation
		CandidateFactory<Solution> candidateFactory = new SOCandidateFactory(createDummyRooms(), createDummySessions(), createDummyStartDates());
		
		List<EvolutionaryOperator<Solution>> operators = new LinkedList<EvolutionaryOperator<Solution>>();
//		operators.add(new CrossoverOperator()); //TODO
		operators.add(new MutateOperator());
		
		EvolutionaryOperator<Solution> evolutionaryOperator = new EvolutionPipeline<Solution>(operators);
		SOFitnessEvaluator fitnessEvaluator = new SOFitnessEvaluator();
		RouletteWheelSelection selectionStrategy = new RouletteWheelSelection();
		Random rng = new org.uncommons.maths.random.MersenneTwisterRNG();
		
		//Creation of the engine
		EvolutionEngine<Solution> engine = new GenerationalEvolutionEngine<Solution>(candidateFactory,evolutionaryOperator,fitnessEvaluator,selectionStrategy,rng);

		//Debugging
		engine.addEvolutionObserver(new EvolutionObserver<Solution>()
		{
		    public void populationUpdate(PopulationData<? extends Solution> data)
		    {
		        System.out.printf("Generation %d: %s\n",
		                          data.getGenerationNumber(),
		                          data.getBestCandidateFitness());
		    }
		});

		//Finding the result
		Solution result = engine.evolve(20, 1, new GenerationCount(50));
		System.out.println(result);
	}
	
	private static List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		result.add(new Room("Aachen"));
		result.add(new Room("Mainz"));
		result.add(new Room("New York"));
		return result;
	}
	
	private static List<Session> createDummySessions(){
		ArrayList<Session> result = new ArrayList<Session>();
		result.add(new Session("Stricken 1x1", createRandomSpeakers(),null));
		result.add(new Session("Bierbrauen leicht gemacht", createRandomSpeakers(),null));
		result.add(new Session("PHP is the new cobol", createRandomSpeakers(),null));
		result.add(new Session("Futurama", createRandomSpeakers(),null));
		result.add(new Session("Java. Eine Insel...", createRandomSpeakers(),null));
		return result;
	}
	
	private static List<String> createRandomSpeakers(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("Speaker #" + System.currentTimeMillis() % 1000);
		return result;
	}
	
	private static List<Date> createDummyStartDates(){
		ArrayList<Date> result = new ArrayList<Date>();
		Calendar c = Calendar.getInstance();
		Date today = new Date();
		c.setTime(today);
		for (int i = 9; i < 12; i++){
			c.set(Calendar.HOUR_OF_DAY, i);
			result.add(c.getTime());
		}
		return result;
	}
}
