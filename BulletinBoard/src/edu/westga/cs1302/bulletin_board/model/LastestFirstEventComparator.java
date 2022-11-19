package edu.westga.cs1302.bulletin_board.model;

import java.util.Comparator;

/**
 * The Class LastestFirstEventComparator.
 * 
 * @author Jeffrey Gaines
 */
public class LastestFirstEventComparator implements Comparator<Event> {

	/**
	 * Latest event first.
	 *
	 * @return the list
	 */
	@Override
	public int compare(Event o1, Event o2) {
		if (o1.getDate().isBefore(o2.getDate())) {
			return 1;
		} else if (o2.getDate().isBefore(o1.getDate())) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Latest First";
	}
}
