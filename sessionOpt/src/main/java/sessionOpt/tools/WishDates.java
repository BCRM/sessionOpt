package sessionOpt.tools;

import java.util.Date;
import java.util.List;

public class WishDates {
	private List<Date> dates;
	private int penalty;
	public WishDates(List<Date> dates, int penalty) {
		super();
		this.dates = dates;
		this.penalty = penalty;
	}
	public List<Date> getDates() {
		return dates;
	}
	public int getPenalty() {
		return penalty;
	}
	
}
