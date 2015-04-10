package sessionOpt.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import sessionOpt.entities.Room;
import sessionOpt.entities.Session;

public class TotalRandomDummyDataCreator extends DummyDataCreator{
	protected static Random r;
	
	public static int numberOfSessions = 35;
	public static int numberOfRooms = 5;

	public static String[] roomNames = new String[] {"Aachen", "Mainz", "Potsdam", "Berlin", "Hamburg", "München", "Dresden", "Dortmund" }; 
	public static String[] peopleNames = new String[] {"Aaron", "Arndt", "Bernd", "Babsi", "Claus", "Christoph", "Detlef", "Dieter", "Edgar", "Ernst", "Felix", "Frauke", "Gabi", "Gerd", "Herbert", "Hannah", "Inge", "Ilka", "Julian", "Jens", "Mark", "Matthias", "Norbert", "Nina", "Olga", "Olaf", "Peter", "Paul", "Quentin", "Qhuhg-Zou", "Robert", "Rainer",  "Sabine", "Sandra", "Tina", "Tim", "Uwe", "Ulf", "Verena", "Vladimir", "Walter", "Wilhelm", "Xander", "Xaver", "Yves", "Ylver", "Zack", "Zofie"};
	public static String[] topics = new String[] {"PHP", "UX", "Java", "Stricken", "SM", "Urlaub", "Startups", "Comics", "Musik", "RM rocks" };
	public static String[] flavors = new String[] {"basics", "leicht gemacht", "for nerds", "hardcore", "for startups", "enterprise" };
	
	
	public TotalRandomDummyDataCreator(Random rng) {
		r = rng;
	}
	
	public List<Session> createDummySessions(List<Date> dates){
		ArrayList<Session> result = new ArrayList<Session>();
		for (int i = 0; i < numberOfSessions; i++){
			String sessionName = topics[r.nextInt(topics.length)] + " " + flavors[r.nextInt(flavors.length)];
			Set<String> speakers = new HashSet<String>();
			for (int j = 0; j < r.nextInt(2) + 1; j++){
				speakers.add(peopleNames[r.nextInt(peopleNames.length)]);
			}
			String speakerNames = "";
			for (String speaker: speakers){
				speakerNames = speakerNames + "," + speaker;
			}
			
			
			int chance = r.nextInt(100);
			Date fixedDate = null;
			if (chance <= 10){
				fixedDate = dates.get(r.nextInt(dates.size()));
			}
			WishDates wishDates = null;
			chance = r.nextInt(100);
			if (chance <= 10){
				List<Date> wishes = new ArrayList<Date>();
				for (Date date: dates){
					chance = r.nextInt(100);
					if (chance <= 30){
						wishes.add(date);
					}
				}
				wishDates = new WishDates(wishes, 50);
			}
			
			result.add(new Session(sessionName, createRandomSpeakers(speakerNames.substring(1)),createDummyAudience(r.nextInt(9) * 10 + 10, r.nextBoolean()), fixedDate, wishDates));		
		}

		return result;
	}
	
	public List<Room> createDummyRooms(){
		ArrayList<Room> result = new ArrayList<Room>();
		Set<String> names = new HashSet<String>();
		while (names.size() < numberOfRooms){
			names.add(roomNames[r.nextInt(roomNames.length)]);
		}
		
		for (String name: names){
			result.add(new Room(name, createDummySeats(r.nextInt(7) * 10 + 30, r.nextBoolean())));
		}
		return result;
	}
	
}
