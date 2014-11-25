package sessionOpt.entities.features;

import sessionOpt.entities.Feature;

public abstract class IntegerFeature implements Feature {
	
	private int size;
	
	public IntegerFeature(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
