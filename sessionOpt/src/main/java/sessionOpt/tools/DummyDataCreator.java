package sessionOpt.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sessionOpt.Penalties;
import sessionOpt.SOFitnessEvaluator;
import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;
import sessionOpt.entities.Room;
import sessionOpt.entities.Session;
import sessionOpt.entities.features.BooleanFeature;
import sessionOpt.entities.features.IntegerFeature;
import sessionOpt.entities.prerequisites.BooleanPrerequisite;
import sessionOpt.entities.prerequisites.IntegerPrerequisite;

public class DummyDataCreator {

	public List<Date> createDummyStartDates(){
		ArrayList<Date> result = new ArrayList<Date>();
		Calendar c = Calendar.getInstance();
		Date today = new Date();
		c.setTime(today);
		for (int i = 9; i <= 14; i++){
			c.set(Calendar.HOUR_OF_DAY, i);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			result.add(c.getTime());
		}
		return result;
	}

	public List<String> createRandomSpeakers(String name){
		ArrayList<String> result = new ArrayList<String>();
		for (String subName: name.split(",")){
			result.add(subName);
		}
		return result;
	}

	public List<Session> createDummySessions(List<Date> dates){
		ArrayList<Session> result = new ArrayList<Session>();
		result.add(new Session("Stricken 1x1", createRandomSpeakers("Achim,AxelF"),createDummyAudience(10, true)));
		result.add(new Session("Stricken 2x2", createRandomSpeakers("Achim,AxelF"),createDummyAudience(10)));
		result.add(new Session("Stricken 3x3", createRandomSpeakers("Achim"),createDummyAudience(10)));
		result.add(new Session("Bierbrauen I", createRandomSpeakers("AxelF"),createDummyAudience(20)));
		result.add(new Session("PHP is the C", createRandomSpeakers("Günni"),createDummyAudience(30)));
		result.add(new Session("Futurama!!!!", createRandomSpeakers("Herbi"),createDummyAudience(40)));
		result.add(new Session("Java? Java!!", createRandomSpeakers("HansS"),createDummyAudience(50)));
		return result;
	}
	
	public Map<String, Prerequisite> createDummyAudience(int size) {
		return createDummyAudience(size, false);
	}
	
	public Map<String, Prerequisite> createDummyAudience(int size, boolean withBeamer) {
		Map<String, Prerequisite> result = new HashMap<String, Prerequisite>();
		result.put("Seats", new IntegerPrerequisite("Seats", size, SOFitnessEvaluator.LARGE_PENALTY, 20, 10, -1));
		if (withBeamer){
			result.put("Beamer", new BooleanPrerequisite("Beamer", SOFitnessEvaluator.MEDIUM_PENALTY * 2));
		}
		return result;
	}

	public List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		result.add(new Room("Aachen", createDummySeats(10, false)));
		result.add(new Room("Mainz", createDummySeats(20, true)));
		result.add(new Room("New York", createDummySeats(50, true)));
		return result;
	}
	
	public Map<String, Feature> createDummySeats(int size, boolean withBeamer) {
		HashMap<String, Feature> result = new HashMap<String, Feature>();
		result.put("Seats", new IntegerFeature("Seats", size));
		if (withBeamer){
			result.put("Beamer", new BooleanFeature("Beamer"));
		}
		return result;
	}

	public Penalties createPenalties() {
		Penalties pen = new Penalties(1000);
		pen.setPenalty(Penalties.SAME_SPEAKER_TWICE_ON_A_DATE, SOFitnessEvaluator.HORRENDOUS_PENALTY);
		pen.setPenalty(Penalties.NOT_MATCHING_FIXED_DATE, SOFitnessEvaluator.VERY_LARGE_PENALTY);
		pen.setPenalty(Penalties.NO_ROOM_FOUND_AT_ALL, SOFitnessEvaluator.VERY_LARGE_PENALTY);
		return pen;
	}

}
