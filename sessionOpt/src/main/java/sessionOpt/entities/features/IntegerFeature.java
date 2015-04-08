package sessionOpt.entities.features;

import sessionOpt.entities.Feature;

public class IntegerFeature implements Feature {
	
	public static String FEATURE_SEATS = "Seats";
	
	private int size;
	private String name;
	
	public IntegerFeature(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}
	
	
}
