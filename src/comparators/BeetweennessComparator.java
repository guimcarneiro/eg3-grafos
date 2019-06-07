package comparators;

import java.util.Comparator;

import entities.User;

public class BeetweennessComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return Double.compare(o2.getBeetweennessScore(), o1.getBeetweennessScore());
	}

}
