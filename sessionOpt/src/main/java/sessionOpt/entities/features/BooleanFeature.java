package sessionOpt.entities.features;

import sessionOpt.entities.Feature;

public class BooleanFeature implements Feature {
	
	private String name;
	
	public BooleanFeature(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
