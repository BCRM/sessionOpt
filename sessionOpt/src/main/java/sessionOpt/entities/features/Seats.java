package sessionOpt.entities.features;


public class Seats extends IntegerFeature {
	
	public Seats(int size) {
		super(size);
	}

	@Override
	public String getName() {
		return "Seats for " + getSize() + " people";
	}
}
