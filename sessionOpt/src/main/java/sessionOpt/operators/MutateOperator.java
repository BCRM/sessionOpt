package sessionOpt.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import sessionOpt.entities.Slot;
import sessionOpt.entities.Solution;
import sessionOpt.tools.Tools;


public class MutateOperator implements EvolutionaryOperator<Solution> {
	
	@Override
	public List<Solution> apply(List<Solution> selectedCandidates, Random rng) {
		ArrayList<Solution> result = new ArrayList<Solution>();
		for (Solution solution: selectedCandidates){
			int totalSize = solution.getSlots().size();
			int rnd1 = rng.nextInt(totalSize);
			int rnd2 = Tools.findSwitchingPartner(rnd1, solution, rng);


			//Damit wir nachher beim entfernen aus der Liste kein Chaos anrichten, sortieren wir die beiden schnell
			if (rnd1 > rnd2){
				int tmp = rnd1;
				rnd1 = rnd2;
				rnd2 = tmp;
			}
			//Neue Liste anlegen und alle rein werfen
			List<Slot> slots = new ArrayList<Slot>(solution.getSlots().size());
			slots.addAll(solution.getSlots());
			Slot slot1 = solution.getSlot(rnd1);
			Slot slot2 = slots.get(rnd2);
			
			//Die beiden erst mal raus
			slots.remove(rnd2);
			slots.remove(rnd1);
			
			//Neu anlegen mit geswitchter Session
			Slot newSlot = new Slot(slot1.getRoom(), slot1.getDate());
			newSlot.setSession(slot2.getSession());
			slots.add(newSlot);
			
			
			newSlot = new Slot(slot2.getRoom(), slot2.getDate());
			newSlot.setSession(slot1.getSession());
			slots.add(newSlot);
			
//			System.out.println("Switched " + slots.get(rnd1).getRoom().getName() + "/"+ slots.get(rnd1).getSession() + " with " + slots.get(rnd2).getRoom().getName() + "/" + slots.get(rnd2).getSession());
			
			
			result.add(new Solution(solution.getRooms(), solution.getDates(), slots, solution.getPenalties()));
		}
		return result;
	}

}
