package sessionOpt.entities;

import java.util.List;

import sessionOpt.entities.features.BooleanFeature;

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
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(getName());
		b.append(" [");
		boolean foundFeature = false;
		for (Feature feat: getFeatures()){
			if (feat instanceof BooleanFeature){
				b.append (feat.getName() + " ");
				foundFeature = true;
			}
		}
		if (foundFeature){
			b.deleteCharAt(b.length()-1);
		}
		b.append("]");
		return b.toString();
	}
}
