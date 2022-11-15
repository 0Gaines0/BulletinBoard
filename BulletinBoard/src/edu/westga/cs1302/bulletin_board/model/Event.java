package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;

/**
 * The Class Event.
 * 
 * @author Jeffrey Gaines
 */
public class Event {

	private static final String DASH = "-";
	private String title;
	private String description;
	private LocalDate date;
	private TypeOfEvent type;

	private enum TypeOfEvent {
		Musical, Theatrical, Political;
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
			throw new IllegalArgumentException("Title must not be null");
		}
		if (description == null) {
			throw new IllegalArgumentException("Description must not be null");
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Title must not be empty");
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description must not be empty");
		}
		if (!(type.equals(TypeOfEvent.Musical) || type.equals(TypeOfEvent.Theatrical)
				|| type.equals(TypeOfEvent.Political))) {
			throw new IllegalArgumentException("Type must be Musical, Theatrical or Political");
		}

		if (date != null) {
			throw new IllegalArgumentException("Date must not be null");
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
		String eventSummary = this.getTitle() + DASH + this.getDescription() + DASH + this.getDate() + DASH
				+ this.getType();
		return eventSummary;
	}

	@Override
	public String toString() {
		return "Event [title=" + this.title + "]";
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
