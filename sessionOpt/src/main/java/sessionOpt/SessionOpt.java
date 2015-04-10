package sessionOpt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
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
import sessionOpt.tools.DummyDataCreator;
import sessionOpt.tools.TotalRandomDummyDataCreator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;



public class SessionOpt {

	private static Solution calculate(Request request, Random rng){
		CandidateFactory<Solution> candidateFactory = new SOCandidateFactory(request.getRooms(), request.getSessions(), request.getDates(), request.getPenalties());
		
		List<EvolutionaryOperator<Solution>> operators = new LinkedList<EvolutionaryOperator<Solution>>();
		//Wir haben nur einen Operator - Nur Mutation. Eine Kombination bring nicht viel
		operators.add(new MutateOperator());
		
		EvolutionaryOperator<Solution> evolutionaryOperator = new EvolutionPipeline<Solution>(operators);
		FitnessEvaluator<Solution> fitnessEvaluator = new SOFitnessEvaluator();
		RouletteWheelSelection selectionStrategy = new RouletteWheelSelection();
		
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

		//Rechne solange, bis sich in 1000 Generationen keine Verbesserung mehr ergibt.
		Solution result = engine.evolve(80, 10, new Stagnation(1000, fitnessEvaluator.isNatural()));
		return result;
	}
	
	/**
	 * Lädt (wenn er nicht einen neuen erzeugen soll) den Seed aus der seed.txt und erzeugt ein neues Random Objekt.
	 * @param createNewSeed
	 * @return Random
	 */
	private static Random prepareRandom(boolean createNewSeed){
		MersenneTwisterRNG rng;
		File seedFile = new File("seed.txt");
		//Wenn wir einen alten Seed haben, verwenden wir den weiter...
		if (seedFile.canRead() && !createNewSeed){
			byte[] seed = (byte[]) new XStream().fromXML(new File("seed.txt"));
			rng = new org.uncommons.maths.random.MersenneTwisterRNG(seed);
		} else {
			//Wir bauen einen neuen seed
			rng = new org.uncommons.maths.random.MersenneTwisterRNG();
			try {
				new XStream().toXML(rng.getSeed(), new FileWriter("seed.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rng;
	}

	/**
	 * Usage:
	 * sessionOpt newSeed -> Creates a new seed
	 * sessionOpt load <Input> [Output] -> Load from inputFile, write result to outputFile or console if empty
	 * sessionOpt Create <RequestOutput> <ResultOutput> -> Create a new random request and reply 
	 * @param args
	 */
	public static void main(String[] args) {
		//Finding the result
		long start = System.currentTimeMillis();
		
		Random rng = prepareRandom(args.length > 0 && args[0].equals("newSeed"));
		Request request;
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
		File outputFile = null;
		if (args.length > 0 && args[0].equals("load")){
			//Sollen wir eine Eingabe laden?
			request = (Request)xstream.fromXML(new File(args[1]));
			//Wenn wir ein drittes Argument haben, ist das die Ausgabedatei
			if (args.length > 2){
				outputFile = new File(args[2]);
			}
		} else {
			//Nein? Dann erzeugen wir was zufälliges
			DummyDataCreator creator = new TotalRandomDummyDataCreator(rng);
			List<Date> dates = creator.createDummyStartDates();
			request = new Request(creator.createDummyRooms(), creator.createDummySessions(dates), dates, creator.createPenalties());
			if (args.length > 1){
				try {
					xstream.toXML(request, new FileWriter(new File(args[1])));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (args.length > 2){
				outputFile = new File(args[2]);
			}
		}
		
		//Die eigentliche Arbeit
		Solution finalSolution = calculate(request, rng);
		System.out.println(finalSolution);
		System.out.println("Calculation took " + (System.currentTimeMillis() - start) + "ms.");
		
		
		//Ausgabe
        if (outputFile != null){
        	try {
				xstream.toXML(finalSolution, new FileWriter(outputFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
    		System.out.println(xstream.toXML(finalSolution));
        }

	}
}
