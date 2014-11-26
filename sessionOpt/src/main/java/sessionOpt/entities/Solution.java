package sessionOpt.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Solution {
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> dates;
	
	private List<Slot> slots = new ArrayList<Slot>();
	private HashMap<Date, HashMap<Room, Slot>> byDate = new HashMap<Date, HashMap<Room, Slot>>();
	
	public Solution(List<Room> rooms, List<Date> dates){
		this.rooms = rooms;
		this.dates = dates;
		for (Room room: rooms){
			for (Date date: dates){
				Slot slot = new Slot(room, date);
				slots.add(slot);
				addSlotByDate(date, slot);
			}
		}
	}
	
	public HashMap<Room, Slot> getSlotsByDate(Date date){
		return byDate.get(date);
	}
	
	public Solution(List<Room> rooms, List<Date> dates, List<Slot> slots){
		this(rooms, dates);
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
				b.append(date.getHours() + " Uhr:");
				if (byDate.get(date).get(room).getSession() != null){
					b.append(byDate.get(date).get(room).getSession()+ " -- COST: " +byDate.get(date).get(room).getHappiness() + "\n");
				} else {
					b.append("------- FREI -------\n");
				}
			}
		}
		return b.toString();
	}
	
}
