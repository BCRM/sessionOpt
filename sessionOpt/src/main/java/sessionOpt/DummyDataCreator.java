package sessionOpt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sessionOpt.entities.Feature;
import sessionOpt.entities.Prerequisite;
import sessionOpt.entities.Room;
import sessionOpt.entities.Session;
import sessionOpt.entities.features.Seats;
import sessionOpt.entities.prerequisites.Audience;

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
		result.add(new Session("Stricken 1x1", createRandomSpeakers(),createDummyAudience(10)));
		result.add(new Session("Bierbrauen leicht gemacht", createRandomSpeakers(),createDummyAudience(20)));
		result.add(new Session("PHP is the new cobol", createRandomSpeakers(),createDummyAudience(30)));
		result.add(new Session("Futurama", createRandomSpeakers(),createDummyAudience(40)));
		result.add(new Session("Java. Eine Insel...", createRandomSpeakers(),createDummyAudience(50)));
		return result;
	}
	
	static List<Prerequisite> createDummyAudience(int size) {
		ArrayList<Prerequisite> result = new ArrayList<Prerequisite>();
		result.add(new Audience(size));
		return result;
	}

	static List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		result.add(new Room("Aachen", createDummySeats(10)));
		result.add(new Room("Mainz", createDummySeats(20)));
		result.add(new Room("New York", createDummySeats(30)));
		return result;
	}
	
	static List<Feature> createDummySeats(int size) {
		ArrayList<Feature> result = new ArrayList<Feature>();
		result.add(new Seats(size));
		return result;
	}

}
