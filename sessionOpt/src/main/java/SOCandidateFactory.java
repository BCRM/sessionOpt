import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;

import entities.Room;
import entities.Session;
import entities.Slot;
import entities.Solution;


public class SOCandidateFactory implements CandidateFactory<Solution> {
	
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> startDates;
	
	public SOCandidateFactory(List<Room> rooms, List<Session> sessions, List<Date> startDates){
		this.rooms = rooms;
		this.sessions = sessions;
		this.startDates = startDates;
	}
 
	@Override
	public List<Solution> generateInitialPopulation(int populationSize, Random rng) {
		List<Solution> result = new ArrayList<Solution>();
		for (int i = 0; i < populationSize; i++){
			result.add(generateRandomCandidate(rng));
		}
		return result;
	}

	@Override
	public List<Solution> generateInitialPopulation(int populationSize,
			Collection<Solution> seedCandidates, Random rng) {
		// TODO What for?
		return generateInitialPopulation(populationSize, rng);
	}

	@Override
	public Solution generateRandomCandidate(Random rng) {
		Solution result = new Solution(rooms, sessions);
		Iterator<Session> it = sessions.iterator();
		for (Room room: rooms){
			for (Date date: startDates){
				if (it.hasNext()){
					result.add(new Slot(room, it.next(), date));
				}
			}
		}
		return result;
	}

}
