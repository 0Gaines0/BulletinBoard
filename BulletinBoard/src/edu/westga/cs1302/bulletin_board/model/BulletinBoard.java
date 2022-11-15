package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import edu.westga.cs1302.bulletin_board.model.Event.TypeOfEvent;

/**
 * The Class BulletinBoard.
 * @author Jeffrey Gaines
 */
public class BulletinBoard {
	private Map<String, Event> eventMap;
	
	/**
	 * Instantiates a new bulletin board.
	 */
	public BulletinBoard() {
		this.eventMap = new HashMap<String, Event>();
	}

	/**
	 * Adds the new event.
	 *
	 * @param newEvent the new event
	 */
	public void addNewEvent(Event newEvent) {
		if (newEvent.getTitle().isEmpty()) {
			throw new IllegalArgumentException("Event being added must have a valid title");
		}
		if (this.eventMap.containsKey(newEvent.getTitle())) {
			throw new IllegalArgumentException("Event title already exist");
		}
		if (newEvent.getDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Event being added must have a date of today or future days");
		}
		if (!(newEvent.getType().equals(TypeOfEvent.Musical) || newEvent.getType().equals(TypeOfEvent.Political)
				|| newEvent.getType().equals(TypeOfEvent.Theatrical))) {
			throw new IllegalArgumentException(
					"Event being added must have a type of Musical, Political or Theatrical");
		}
		this.eventMap.put(newEvent.getTitle(), newEvent);
	}
	
	/**
	 * Removes the specific event.
	 *
	 * @param title the title
	 */
	public void removeSpecificEvent(String title) {
		this.eventMap.remove(title);
	}
	
	/**
	 * Gets the event map.
	 *
	 * @return the event map
	 */
	public Map<String, Event> getEventMap() {
		return this.eventMap;
	}
}
