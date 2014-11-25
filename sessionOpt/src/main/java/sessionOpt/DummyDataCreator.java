package sessionOpt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sessionOpt.entities.Room;
import sessionOpt.entities.Session;

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
		result.add(new Session("Stricken 1x1", createRandomSpeakers(),null));
		result.add(new Session("Bierbrauen leicht gemacht", createRandomSpeakers(),null));
		result.add(new Session("PHP is the new cobol", createRandomSpeakers(),null));
		result.add(new Session("Futurama", createRandomSpeakers(),null));
		result.add(new Session("Java. Eine Insel...", createRandomSpeakers(),null));
		return result;
	}

	static List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		result.add(new Room("Aachen"));
		result.add(new Room("Mainz"));
		result.add(new Room("New York"));
		return result;
	}

}
