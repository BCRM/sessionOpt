package entities;

import java.util.List;

import entities.Prerequisite;

public class Session {
	private String name;
	private List<String> speaker;
	private List<Prerequisite> preRequisites;
	
	
	public Session(String name, List<String> speaker,
			List<Prerequisite> preRequisites) {
		super();
		this.name = name;
		this.speaker = speaker;
		this.preRequisites = preRequisites;
	}


	public String getName() {
		return name;
	}


	public List<String> getSpeaker() {
		return speaker;
	}


	public List<Prerequisite> getPreRequisites() {
		return preRequisites;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
