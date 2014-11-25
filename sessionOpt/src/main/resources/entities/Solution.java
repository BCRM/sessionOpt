package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	private List<Room> rooms;
	private List<Session> sessions;
	
	private List<Slot> slots = new ArrayList<Slot>();
	private Map<Date, List<Slot>> byDate = new HashMap<Date, List<Slot>>();
	
	public Solution(List<Room> rooms, List<Session> sessions){
		this.rooms = rooms;
		this.sessions = sessions;
	}
	
	public Solution(List<Room> rooms, List<Session> sessions, List<Slot> slots){
		this(rooms, sessions);
		this.slots = slots;
	}
	
	public void add(Slot slot){
		slots.add(slot);
		List<Slot> slotsAtDate = byDate.get(slot.getDate());
		if (slotsAtDate == null){
			slotsAtDate = new ArrayList<Slot>();
			byDate.put(slot.getDate(), slotsAtDate);
		}
		slotsAtDate.add(slot);
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

	
	
}
