package edu.westga.cs1302.bulletin_board.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Class LastestFirstEventComparator.
 * 
 * @author Jeffrey Gaines
 */
public abstract class LastestFirstEventComparator implements Comparator<Event> {
	private List<Event> listOfEvents;

	/**
	 * Latest event first.
	 *
	 * @return the list
	 */
	public List<Event> latestEventFirst() {
		Comparator<Event> latestEventComparator = new Comparator<Event>() {
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
		};

		List<Event> latestEventsFirst = new ArrayList<Event>(this.listOfEvents);
		Collections.sort(latestEventsFirst, latestEventComparator);
		return latestEventsFirst;
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
