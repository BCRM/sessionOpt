package sessionOpt.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sessionOpt.entities.Slot;
import sessionOpt.entities.Solution;

public class Tools {
	public static String appendSpaces(String string, int totalSize){
		while (string.length() < totalSize){
			string = string + " ";
		}
		return string;
	}

	public static int findSwitchingPartner(int original, Solution solution, Random rng){
		List<Integer> possiblePartners = new ArrayList<Integer>();
		List<Integer> lastDitchPossiblePartners = new ArrayList<Integer>();
		Slot slot1 = solution.getSlot(original);
		for (int i = 0; i < solution.getSlots().size(); i++){
			//Ein Switch auf sich selbst ist eigentlich immmer ausgeschlossen.
			if (i == original){
				continue;
			}
			
			//Haben wir ein fixed Date?
			if (slot1.getSession() != null && slot1.getSession().getFixedDate() != null){
				//Dann switchen wir nur, wenn der andere auch am gleichen Date statt findet.
				if (slot1.getSession().getFixedDate().equals(solution.getSlot(i).getDate())){
					//Ok, prinzipiell ja. Aber nur wenn der leer ist,  nicht auch fixed Date ist oder eh schon falsch liegt..
					if (solution.getSlot(i).getSession() == null || solution.getSlot(i).getSession().getFixedDate() == null || !solution.getSlot(i).getSession().getFixedDate().equals(solution.getSlot(i).getDate())){
						possiblePartners.add(i);
					} else {
						//Also theoretisch ist das zumindest die richtige Uhrzeit. Wenn es also gar nicht anders geht...
						lastDitchPossiblePartners.add(i);
					}
				}
			} else {
				possiblePartners.add(i);
			}
		}
		
		//Ausnahme: Es gibt keinen sinnvollen Partner, (z.B. nur ein Raum und fixedDate)
		if (possiblePartners.size() == 0){
			//Gibt es überhaupt keinen? Nicht mal einen komischen?
			if (lastDitchPossiblePartners.size() == 0){
				return original;
			} else {
				//Es gibt welche, die sind aber alle schlecht. Egal, vielleicht aufgrund der anderen Kriterien besser als der hier....
				return lastDitchPossiblePartners.get(rng.nextInt(lastDitchPossiblePartners.size()));
			}
		}
		return possiblePartners.get(rng.nextInt(possiblePartners.size()));
	}
	
}
