package operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import entities.Slot;
import entities.Solution;

public class MutateOperator implements EvolutionaryOperator<Solution> {

	@Override
	public List<Solution> apply(List<Solution> selectedCandidates, Random rng) {
		ArrayList<Solution> result = new ArrayList<Solution>();
		for (Solution solution: selectedCandidates){
			int totalSize = solution.getSlots().size();
			int rnd1 = rng.nextInt(totalSize);
			int rnd2 = rng.nextInt(totalSize);
			List<Slot> slots = solution.getSlots();
			Slot tmp = slots.get(rnd1);
			slots.set(rnd1, slots.get(rnd2));
			slots.set(rnd2, tmp);
			System.out.println("Switched " + slots.get(rnd1).getRoom().getName() + "/"+ slots.get(rnd1).getSession().getName() + " with " + slots.get(rnd2).getRoom().getName() + "/" + slots.get(rnd2).getSession().getName());
			result.add(new Solution(solution.getRooms(), solution.getSessions(), slots));
		}
		return result;
	}

}
