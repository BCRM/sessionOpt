package entities;

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
	
	public Solution(List<Room> rooms, List<Date> dates, List<Slot> slots){
		this(rooms, dates);
		this.slots = slots;
	}
	
	private void addSlotByDate(Date date, Slot slot){
		HashMap<Room, Slot> slotsAtDate = byDate.get(date);
		if (slotsAtDate == null){
			slotsAtDate = new HashMap<Room, Slot>();
			byDate.put(date, slotsAtDate);
		}
		slotsAtDate.put(slot.getRoom(), slot);
	}
	
	
	public void add(Session session, Room room, Date date){
		Slot slot = byDate.get(date).get(room);
		slot.setSession(session);
		slots.add(slot);
	}

	public List<Slot> getSlots() {
		return slots;
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
	

	
	
}
