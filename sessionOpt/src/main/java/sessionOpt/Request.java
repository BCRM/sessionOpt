package sessionOpt;

import java.util.Date;
import java.util.List;

import sessionOpt.entities.Room;
import sessionOpt.entities.Session;

public class Request {
	
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> dates;
	private Penalties penalties;
	 
	public Request(List<Room> rooms, List<Session> sessions, List<Date> dates, Penalties penalties){
		this.rooms = rooms;
		this.sessions = sessions;
		this.dates = dates;
		this.penalties = penalties;
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

	public Penalties getPenalties() {
		return penalties;
	}
	
	
	
	
}
