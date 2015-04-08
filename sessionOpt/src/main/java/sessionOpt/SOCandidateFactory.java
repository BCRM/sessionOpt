package sessionOpt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;

import sessionOpt.entities.Room;
import sessionOpt.entities.Session;
import sessionOpt.entities.Slot;
import sessionOpt.entities.Solution;
import sessionOpt.tools.Tools;



public class SOCandidateFactory implements CandidateFactory<Solution> {
	
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> startDates;
	private Penalties penalties;
	
	public SOCandidateFactory(List<Room> rooms, List<Session> sessions, List<Date> startDates, Penalties penalties){
		this.rooms = rooms;
		this.sessions = sessions;
		this.startDates = startDates;
		this.penalties = penalties;
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
		Solution result = new Solution(rooms, startDates, sessions.size(), penalties);
		
		//Wir verteilen die Sessions zufällig auf die Räume
		for (Session session: sessions){
			int rnd = rng.nextInt(result.getAmountOfSlots());
			while (result.getSlot(rnd).getSession() != null){
				rnd = rng.nextInt(result.getAmountOfSlots());
			}
			result.getSlot(rnd).setSession(session);
		}
		
		//Korrekturlauf für FixedDates
		for (int i = 0; i < result.getSlots().size(); i++){
			Slot slot = result.getSlots().get(i);
			//Alle falsch zugeordneten Slots finden
			if (slot.getSession() != null && slot.getSession().getFixedDate() != null && !slot.getSession().getFixedDate().equals(slot.getDate())){
				int switchPartner = Tools.findSwitchingPartner(i, result, rng);
				if (switchPartner != i){
					//Wir switchen die Sessions
					Session old = result.getSlot(switchPartner).getSession();
					result.getSlot(switchPartner).setSession(slot.getSession());
					slot.setSession(old);
				}
			}
		}
		
		return result;
	}

}

