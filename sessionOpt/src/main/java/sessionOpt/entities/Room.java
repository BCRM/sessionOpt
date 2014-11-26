package sessionOpt.entities;

import java.util.Map;

import sessionOpt.entities.features.BooleanFeature;

public class Room {
	private String name;
	private Map<String, Feature> features;
	
	public Room(String name, Map<String, Feature> features){
		this.name = name;
		this.features = features;
	}
	
	public void addFeature(Feature f){
		features.put(f.getName(), f);
	}
	
	public String getName() {
		return name;
	}
	
	
	public Map<String, Feature> getFeatures() {
		return features;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(getName());
		b.append(" [");
		boolean foundFeature = false;
		for (Feature feat: getFeatures().values()){
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
