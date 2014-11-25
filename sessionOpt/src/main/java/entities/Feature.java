package entities;

import entities.Prerequisite;

public interface Feature {
	public boolean fullfills(Prerequisite req);
	public String getName();
}
