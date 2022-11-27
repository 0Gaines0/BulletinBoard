package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import edu.westga.cs1302.bulletin_board.model.Event.TypeOfEvent;
import edu.westga.cs1302.bulletin_board.resources.UI;

/**
 * The Class BulletinBoard.
 * 
 * @author Jeffrey Gaines
 */
public class BulletinBoard implements Collection<Event> {
	private static final String EVENT_MAP_EMPTY = "Event Map is empty";
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
	 * @return true, if successful
	 */
	public boolean addNewEvent(Event newEvent) {
		if (newEvent == null) {
			throw new NullPointerException(UI.NULL_EVENT);
		}
		if (this.eventMap.containsKey(newEvent.getTitle())) {
			throw new IllegalArgumentException(UI.EVENT_EXIST);
		}
		if (newEvent.getDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(UI.PAST_DATE);
		}
		return this.eventMap.put(newEvent.getTitle(), newEvent) == null;
	}

	/**
	 * Gets the sorted event of list.
	 *
	 * @param eventComparator the event comparator
	 * @param typeFilter      the type filter
	 * @return the sorted event of list
	 */
	public List<Event> getSortedEventOfList(Comparator<Event> eventComparator, TypeOfEvent typeFilter) {
		if (this.eventMap.isEmpty()) {
			throw new IllegalArgumentException("eventMap can not be empty");
		}
		List<Event> sortedList = new ArrayList<Event>();
		sortedList.addAll(this.eventMap.values());

		if (eventComparator == null && typeFilter == null) {
			return sortedList;
		} else if (eventComparator == null && typeFilter != null) {
			List<Event> filteredList = new ArrayList<Event>();
			for (Event currEvent : sortedList) {
				if (currEvent.getType().equals(typeFilter)) {
					filteredList.add(currEvent);
				}
			}
			return filteredList;
		} else if (eventComparator != null && typeFilter == null) {
			List<Event> comparatorList = new ArrayList<Event>();
			for (Event currEvent : sortedList) {
				comparatorList.add(currEvent);
			}
			comparatorList.sort(eventComparator);
			return comparatorList;
		} else {
			List<Event> comparatorFilteredList = new ArrayList<Event>();
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
	 * @return true, if successful
	 */
	public boolean removeSpecificEvent(String title) {
		if (this.eventMap.isEmpty()) {
			throw new IllegalArgumentException(EVENT_MAP_EMPTY);
		}
		return this.eventMap.remove(title) != null;

	}

	/**
	 * Gets the event map.
	 *
	 * @return the event map
	 */
	public Map<String, Event> getEventMap() {
		if (this.eventMap.isEmpty()) {
			throw new IllegalArgumentException(EVENT_MAP_EMPTY);
		}
		return this.eventMap;
	}

	@Override
	public int size() {
		return this.eventMap.size();
	}

	@Override
	public boolean isEmpty() {
		return this.eventMap.isEmpty();
	}

	@Override
	public boolean contains(Object event) {
		if (event == null) {
			throw new NullPointerException(UI.NULL_EVENT);
		}
		return this.eventMap.containsValue(event);
	}

	@Override
	public Iterator<Event> iterator() {
		return this.eventMap.values().iterator();
	}

	@Override
	public Object[] toArray() {
		return this.eventMap.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] events) {
		return this.eventMap.values().toArray(events);
	}

	@Override
	public boolean add(Event newEvent) {
		if (newEvent == null) {
			throw new NullPointerException(UI.NULL_EVENT);
		}
		if (this.eventMap.containsKey(newEvent.getTitle())) {
			throw new IllegalArgumentException(UI.EVENT_EXIST);
		}
		if (newEvent.getDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(UI.PAST_DATE);
		}
		return this.eventMap.put(newEvent.getTitle(), newEvent) == null;
	}

	@Override
	public boolean remove(Object event) {
		if (event == null) {
			throw new NullPointerException(UI.NULL_EVENT);
		}
		return this.eventMap.remove(((Event) event).getTitle()) != null;
	}

	@Override
	public boolean containsAll(Collection<?> events) {
		if (events == null) {
			throw new NullPointerException(UI.NULL_COLLECTIONS);
		}
		if (events.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}

		return this.eventMap.values().containsAll(events);
	}

	@Override
	public boolean addAll(Collection<? extends Event> events) {
		if (events == null) {
			throw new NullPointerException(UI.NULL_COLLECTIONS);
		}

		if (events.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}
		boolean changed = false;
		for (Event event : events) {
			if (this.add(event)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean removeAll(Collection<?> events) {
		if (events == null) {
			throw new NullPointerException(UI.NULL_COLLECTIONS);
		}
		if (events.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}
		boolean changed = false;
		for (Object event : events) {
			if (this.remove(event)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> events) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		this.eventMap.clear();
	}

}
