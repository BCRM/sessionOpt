package sessionOpt.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sessionOpt.Penalties;
import sessionOpt.entities.features.IntegerFeature;
import sessionOpt.tools.Tools;

public class Solution {
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> dates;
	private final Penalties penalties;
	private static List<String> remarks = new ArrayList<String>();
	
	private List<Slot> slots = new ArrayList<Slot>();
	private HashMap<Date, HashMap<Room, Slot>> byDate = new HashMap<Date, HashMap<Room, Slot>>();
	
	public Solution(List<Room> rooms, List<Date> dates, int amountOfSessions, Penalties penalties){
		//Wenn wir zuwenig Platz haben, machen wir virtuelle Overflow-Räume
		int i = 1;
		while (rooms.size() * dates.size() < amountOfSessions){
			addRemark("Had to add an Overflow Room since there were " + amountOfSessions + " sessions for " + (rooms.size() * dates.size()) + " slots.");
			Map<String, Feature> features = new HashMap<String, Feature>();
			rooms.add(new Room("Overflow #" + i++, features, true));
		}
		this.rooms = rooms;
		this.dates = dates;
		this.penalties = penalties;
	
		//Wir kreieren die Slots anhand der Räume und Daten
		for (Room room: rooms){
			for (Date date: dates){
				Slot slot = new Slot(room, date);
				slots.add(slot);
				addSlotByDate(date, slot);
			}
		}
		
		//Für die Ausgabe das schön sortieren
		Collections.sort(rooms, new Comparator<Room>(){
			@Override
			public int compare(Room o1, Room o2) {
				IntegerFeature r1 = (IntegerFeature) o1.getFeatures().get("Seats");
				IntegerFeature r2 = (IntegerFeature) o2.getFeatures().get("Seats");
				if (r1 == null && r2 == null){
					return o1.getName().compareTo(o2.getName());
				} else if (r1 == null){
					return -1;
				} else if (r2 == null){
					return 1;
				}
				
				if (r1.getSize() > r2.getSize()){
					return 1;
				} else if (r1.getSize() < r2.getSize()){
					return -1;
				} else return o1.getName().compareTo(o2.getName());
			}
		});
		
	}
	
	public HashMap<Room, Slot> getSlotsByDate(Date date){
		return byDate.get(date);
	}
	
	public Solution(List<Room> rooms, List<Date> dates, List<Slot> slots, Penalties penalties){
		this(rooms, dates, -1, penalties);
		this.slots = slots;
	
		//Lookup-Table aktuell halten
		for (Slot slot: slots){
			addSlotByDate(slot.getDate(), slot);
		}
	}
	
	private void addSlotByDate(Date date, Slot slot){
		HashMap<Room, Slot> slotsAtDate = byDate.get(date);
		if (slotsAtDate == null){
			slotsAtDate = new HashMap<Room, Slot>();
			byDate.put(date, slotsAtDate);
		}
		slotsAtDate.put(slot.getRoom(), slot);
	}
	
	
	
	public List<Slot> getSlots() {
		return slots;
	}

	public int getAmountOfSlots(){
		return slots.size();
	}
	
	public Slot getSlot(int index){
		return slots.get(index);
	}


	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public List<Date> getDates() {
		return dates;
	}
	

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (Room room: getRooms()){
			b.append("-------- " + room + "--------\n");
			for (Date date: getDates()){
				b.append(Tools.appendSpaces(Integer.toString(date.getHours()), 2) + " Uhr:");
				if (byDate.get(date).get(room).getSession() != null){
					b.append(byDate.get(date).get(room).getSession()+ " -- COST: " +byDate.get(date).get(room).getHappiness(penalties) + "\n");
				} else {
					b.append("------- FREI -------\n");
				}
			}
			b.append("\n");
		}
		if (getRemarks().size() > 0){
			b.append("\n");
			b.append("Remarks:\n");
			for (String string: getRemarks()){
				b.append(string + "\n");
			}
		}
		return b.toString();
	}

	public Penalties getPenalties() {
		return penalties;
	}
	
	public void addRemark(String s){
		remarks.add(s);
	}

	public List<String> getRemarks() {
		return remarks;
	}
		
}
