package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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
	 * Gets the sorted event of list.
	 *
	 * @param eventComparator the event comparator
	 * @param typeFilter      the type filter
	 * @return the sorted event of list
	 */
	public ArrayList<Event> getSortedEventOfList(Comparator<Event> eventComparator, TypeOfEvent typeFilter) {
		if (this.eventMap.isEmpty()) {
			throw new IllegalArgumentException("eventMap can not be empty");
		}
		ArrayList<Event> sortedList = new ArrayList<Event>();
		sortedList.addAll(this.eventMap.values());

		if (eventComparator == null && typeFilter == null) {
			return sortedList;
		} else if (eventComparator == null && typeFilter != null) {
			ArrayList<Event> filteredList = new ArrayList<Event>();
			for (Event currEvent : sortedList) {
				if (currEvent.getType().equals(typeFilter)) {
					filteredList.add(currEvent);
				}
			}
			return filteredList;
		} else if (eventComparator != null && typeFilter == null) {
			ArrayList<Event> comparatorList = new ArrayList<Event>();
			for (Event currEvent : sortedList) {
				comparatorList.add(currEvent);
			}
			comparatorList.sort(eventComparator);
			return comparatorList;
		} else {
			ArrayList<Event> comparatorFilteredList = new ArrayList<Event>();
			for (Event currEvent : sortedList) {
				if (currEvent.getType().equals(typeFilter)) {
					comparatorFilteredList.add(currEvent);
				}
			}
			comparatorFilteredList.sort(eventComparator);
			return comparatorFilteredList;
		}
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
