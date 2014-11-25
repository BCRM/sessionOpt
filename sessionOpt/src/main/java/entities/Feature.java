package entities;

public interface Feature {
	public boolean fullfills(Prerequisite req);
	public String getName();
}
