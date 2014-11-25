package sessionOpt.entities;

import java.util.List;

public class Room {
	private String name;
	private List<Feature> features;
	
	public Room(String name, List<Feature> features){
		this.name = name;
		this.features = features;
	}
	
	public void addFeature(Feature f){
		features.add(f);
	}
	
	public String getName() {
		return name;
	}
	
	
	public List<Feature> getFeatures() {
		return features;
	}
}
