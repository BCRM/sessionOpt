package entities;

import java.util.List;

public class Room {
	private String name;
	private List<Feature> features;
	
	public Room(String name){
		this.name = name;
	}
	
	public void addFeature(Feature f){
		features.add(f);
	}
	
	public boolean fullfillsPrerequisite(Prerequisite req){
		for (Feature f: features){
			if (f.fullfills(req)){
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	
	public List<Feature> getFeatures() {
		return features;
	}
}