package edu.westga.cs1302.bulletin_board.model;

import java.util.Comparator;

/**
 * The Class EarliestFirstEventComparator.
 * @author Jeffrey Gaines
 */
public class EarliestFirstEventComparator implements Comparator<Event> {
	
	/**
	 * Earliest first event.
	 *
	 * @return the list
	 */
	@Override
	public int compare(Event o1, Event o2) {
		if (o1.getDate().isAfter(o2.getDate())) {
			return 1;
		} else if (o2.getDate().isAfter(o1.getDate())) {
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
		return "Earlist First";
	}
	
}


	
	
	


