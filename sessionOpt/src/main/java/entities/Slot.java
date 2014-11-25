package entities;

import java.util.Date;

public class Slot {
	private Room room;
	private Session session;
	private Date date;
	
	public Slot(Room room, Session session, Date date){
		this.room = room;
		this.session = session;
		this.date = date;
	}

	public Room getRoom() {
		return room;
	}

	public Session getSession() {
		return session;
	}

	public Date getDate() {
		return date;
	}
	
	public int getScore() {
return 1;	}
	
}
