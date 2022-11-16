package edu.westga.cs1302.bulletin_board.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Class EarliestFirstEventComparator.
 * @author Jeffrey Gaines
 */
public abstract class EarliestFirstEventComparator implements Comparator<Event> {
	private List<Event> listOfEvents;
	
	/**
	 * Earliest first event.
	 *
	 * @return the list
	 */
	public List<Event> earliestFirstEvent() {
		Comparator<Event> earliestEventComparator = new Comparator<Event>() {
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
		};
		
		List<Event> earlyFirstEvents = new ArrayList<Event>(this.listOfEvents);
		Collections.sort(earlyFirstEvents, earliestEventComparator);
		return earlyFirstEvents;
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
