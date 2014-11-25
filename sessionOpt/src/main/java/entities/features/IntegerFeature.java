package entities.features;

import entities.Feature;

public abstract class IntegerFeature implements Feature {
	
	private int size;
	
	public IntegerFeature(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
