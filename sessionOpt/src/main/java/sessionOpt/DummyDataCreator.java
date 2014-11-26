package sessionOpt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;
import sessionOpt.entities.Room;
import sessionOpt.entities.Session;
import sessionOpt.entities.features.BooleanFeature;
import sessionOpt.entities.features.IntegerFeature;
import sessionOpt.entities.prerequisites.BooleanPrerequisite;
import sessionOpt.entities.prerequisites.IntegerPrerequisite;

public class DummyDataCreator {
	
	private static int count = 1;

	static List<Date> createDummyStartDates(){
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

	static List<String> createRandomSpeakers(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("Speaker #" + count++);
		return result;
	}

	static List<Session> createDummySessions(){
		ArrayList<Session> result = new ArrayList<Session>();
		result.add(new Session("Stricken 1x1", createRandomSpeakers(),createDummyAudience(10, true)));
		result.add(new Session("Stricken 2x2", createRandomSpeakers(),createDummyAudience(20)));
		result.add(new Session("Stricken 3x3", createRandomSpeakers(),createDummyAudience(10)));
		result.add(new Session("Bierbrauen leicht gemacht", createRandomSpeakers(),createDummyAudience(20)));
		result.add(new Session("PHP is the new cobol", createRandomSpeakers(),createDummyAudience(30)));
		result.add(new Session("Futurama", createRandomSpeakers(),createDummyAudience(40)));
		result.add(new Session("Java. Eine Insel...", createRandomSpeakers(),createDummyAudience(50)));
		return result;
	}
	
	static List<Prerequisite> createDummyAudience(int size) {
		return createDummyAudience(size, false);
	}
	
	static List<Prerequisite> createDummyAudience(int size, boolean withBeamer) {
		ArrayList<Prerequisite> result = new ArrayList<Prerequisite>();
		result.add(new IntegerPrerequisite("Seats", size));
		if (withBeamer){
			result.add(new BooleanPrerequisite("Beamer"));
		}
		return result;
	}

	static List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		result.add(new Room("Aachen", createDummySeats(10, false)));
		result.add(new Room("Mainz", createDummySeats(20, true)));
		result.add(new Room("New York", createDummySeats(50, true)));
		return result;
	}
	
	static List<Feature> createDummySeats(int size, boolean withBeamer) {
		ArrayList<Feature> result = new ArrayList<Feature>();
		result.add(new IntegerFeature("Seats", size));
		if (withBeamer){
			result.add(new BooleanFeature("Beamer"));
		}
		return result;
	}

}
