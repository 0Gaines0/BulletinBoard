package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;

import edu.westga.cs1302.bulletin_board.resources.UI;

/**
 * The Class Event.
 * 
 * @author Jeffrey Gaines
 */
public class Event {

	private static final String COLON = " : ";
	private String title;
	private String description;
	private LocalDate date;
	private TypeOfEvent type;

	public enum TypeOfEvent {
		Musical, Theatrical, Political;
	}
	
	/**
	 * Instantiates a new event.
	 */
	public Event() {
		this.title = "default";
		this.description = "description";
		this.date = LocalDate.now();
		this.type = null;
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param title       the title
	 * @param description the description
	 * @param date        the date
	 * @param type        the type
	 */
	public Event(String title, String description, LocalDate date, TypeOfEvent type) {
		if (title == null) {
			throw new IllegalArgumentException(UI.NULL_TITLE);
		}
		if (type == null) {
			throw new IllegalArgumentException("Type of event must be a valid type");
		}
		if (title.length() == 0) {
			throw new IllegalArgumentException(UI.EMPTY_TITLE);
		}
		if (!(type.equals(TypeOfEvent.Musical) || type.equals(TypeOfEvent.Theatrical)
				|| type.equals(TypeOfEvent.Political))) {
			throw new IllegalArgumentException("Type must be Musical, Theatrical or Political");
		}
		if (date == null) {
			throw new IllegalArgumentException(UI.DATE_NULL);
		}

		this.title = title;
		this.description = description;
		this.date = date;
		this.type = type;

	}
	
	
	/**
	 * Gets the event summary.
	 *
	 * @return the event summary
	 */
	public String getEventSummary() {
		String eventSummary = this.getTitle() + COLON + this.getDescription() + COLON + this.getDate() + COLON
				+ this.getType();
		return eventSummary;
	}

	@Override
	public String toString() {
		return  this.title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TypeOfEvent getType() {
		return this.type;
	}
	
}
