package sessionOpt.entities;

import java.util.Date;

import sessionOpt.Penalties;


public class Slot {
	private Room room;
	private Session session;
	private Date date;
	private int occuredPenalties;
	
	public Slot(Room room, Date date){
		this.room = room;
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

	public void setSession(Session session) {
		this.session = session;
	}
	
	public int getHappiness(Penalties pen) {
		if (session == null) {
			occuredPenalties = 0; 
		} else {
			occuredPenalties = session.getHappiness(room, date, pen);
		}
		return occuredPenalties;
	}

}
