package comparators;

import java.util.Comparator;

import entities.User;

public class AlphaScoreComparator  implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return Double.compare(o2.getAlphaScore(), o1.getAlphaScore());
	}

	
	
}
