package sessionOpt.entities.prerequisites;

import sessionOpt.entities.Prerequisite;

public abstract class IntegerPrerequisite implements Prerequisite {
	
	private int size;
	
	public IntegerPrerequisite(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
