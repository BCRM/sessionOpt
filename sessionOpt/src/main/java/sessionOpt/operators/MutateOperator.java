package sessionOpt.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import sessionOpt.entities.Session;
import sessionOpt.entities.Slot;
import sessionOpt.entities.Solution;


public class MutateOperator implements EvolutionaryOperator<Solution> {

	@Override
	public List<Solution> apply(List<Solution> selectedCandidates, Random rng) {
		ArrayList<Solution> result = new ArrayList<Solution>();
		for (Solution solution: selectedCandidates){
			int totalSize = solution.getSlots().size();
			int rnd1 = rng.nextInt(totalSize);
			int rnd2 = rng.nextInt(totalSize);
			//Nicht aufs gleiche. Das ist dumpf...
			while (rnd2 == rnd1 && totalSize > 1){
				rnd2 = rng.nextInt(totalSize);
			}
			
			List<Slot> slots = new ArrayList<Slot>(solution.getSlots().size());
			slots.addAll(solution.getSlots());

			Session tmp = slots.get(rnd1).getSession();
			slots.get(rnd1).setSession(slots.get(rnd2).getSession());
			slots.get(rnd2).setSession(tmp);

			
//			System.out.println("Switched " + slots.get(rnd1).getRoom().getName() + "/"+ slots.get(rnd1).getSession() + " with " + slots.get(rnd2).getRoom().getName() + "/" + slots.get(rnd2).getSession());
			
			
			result.add(new Solution(solution.getRooms(), solution.getDates(), slots));
		}
		return result;
	}

}
