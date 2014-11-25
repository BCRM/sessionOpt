package entities;

import java.util.Date;

import entities.Room;
import entities.Session;

public class Slot {
	private Room room;
	private Session session;
	private Date date;
	
	public Slot(Room room, Date date){
		this.room = room;
		this.date = date;
	}
	
//	public Slot(Room room, Date date, Session session){
//		this.room = room;
//		this.session = session;
//		this.date = date;
//	}

	public Room getRoom() {
		return room;
	}

	public Session getSession() {
		return session;
	}

	public Date getDate() {
		return date;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	
	
	
}
