package edu.westga.cs1302.bulletin_board.viewmodel;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Event.TypeOfEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The Class BulletinBoardCodeBehind.
 * 
 * @author Jeffrey Gaines
 */
public class BulletinBoardViewModel {
	private BulletinBoard board;
	private StringProperty eventTitleProperty;
	private StringProperty eventDescriptionProperty;
	private ObjectProperty<LocalDate> eventDateProperty;
	private ObjectProperty<TypeOfEvent> eventTypeProperty;

	private ObjectProperty<Comparator<Event>> listOrderObjectProperty;
	private ObjectProperty<TypeOfEvent> listTypeObjectProperty;
	private ObjectProperty<Event> selectedEventProperty;

	private ListProperty<Event> eventListProperty;
	
	private final BooleanProperty eventListEmpty;

	/**
	 * Instantiates a new bulletin board code behind.
	 */
	public BulletinBoardViewModel() {
		this.board = new BulletinBoard();
		new Event();

		this.eventTitleProperty = new SimpleStringProperty();
		this.eventDescriptionProperty = new SimpleStringProperty();
		this.eventDateProperty = new SimpleObjectProperty<LocalDate>();
		this.eventTypeProperty = new SimpleObjectProperty<TypeOfEvent>();

		this.listOrderObjectProperty = new SimpleObjectProperty<Comparator<Event>>();
		this.listTypeObjectProperty = new SimpleObjectProperty<TypeOfEvent>();
		this.selectedEventProperty = new SimpleObjectProperty<Event>();

		this.eventListProperty = new SimpleListProperty<Event>(FXCollections.observableArrayList(this.board));
		this.eventListEmpty = new SimpleBooleanProperty(this.eventListProperty.isEmpty());
	}

	
	/**
	 * 
	 * Adds the event.
	 *
	 * @return true, if successful
	 */
	public boolean addEvent() {
		String title = this.eventTitleProperty.get();
		String description = this.eventDescriptionProperty.get();
		LocalDate date = this.eventDateProperty.get();
		TypeOfEvent type = this.eventTypeProperty.get();

		Event aEvent = null;
		aEvent = new Event(title, description, date, type);

		if (this.board.addNewEvent(aEvent)) {
			this.clear();
			this.updateDisplay();
			return true;
		}
		return false;
	}

	/**
	 * Removes the event.
	 *
	 * @return true, if successful
	 */
	public boolean removeEvent() {
		String title = this.eventTitleProperty.get();

		if (this.board.removeSpecificEvent(title)) {
			this.clear();
			this.updateDisplay();
			return true;
		}

		return false;
	}

	/**
	 * Filter by type and comparator.
	 */
	public void filterByTypeAndComparator() {
		Comparator<Event> listOrder = this.listOrderObjectProperty.get();
		TypeOfEvent filterType = this.listTypeObjectProperty.get();

		List<Event> sortedList = this.board.getSortedEventOfList(listOrder, filterType);
		this.eventListProperty.set(FXCollections.observableArrayList(sortedList));
	}

	private void clear() {
		this.eventTitleProperty.set("");
		this.eventDescriptionProperty.set("");
		this.eventDateProperty.set(null);
		this.eventTypeProperty.set(null);
	}

	private void updateDisplay() {
		this.eventListProperty.set(FXCollections.observableArrayList(this.board));
		this.eventListEmpty.setValue(this.eventListProperty.isEmpty());
	}

	/**
	 * Gets the event title property.
	 *
	 * @return the event title property
	 */
	public StringProperty getEventTitleProperty() {
		return this.eventTitleProperty;
	}

	/**
	 * Gets the event description property.
	 *
	 * @return the event description property
	 */
	public StringProperty getEventDescriptionProperty() {
		return this.eventDescriptionProperty;
	}

	/**
	 * Gets the event date property.
	 *
	 * @return the event date property
	 */
	public ObjectProperty<LocalDate> getEventDateProperty() {
		return this.eventDateProperty;
	}

	/**
	 * Gets the event type property.
	 *
	 * @return the event type property
	 */
	public ObjectProperty<TypeOfEvent> getEventTypeProperty() {
		return this.eventTypeProperty;
	}

	/**
	 * Gets the list order object property.
	 *
	 * @return the list order object property
	 */
	public ObjectProperty<Comparator<Event>> getListOrderObjectProperty() {
		return this.listOrderObjectProperty;
	}

	/**
	 * Gets the list type object property.
	 *
	 * @return the list type object property
	 */
	public ObjectProperty<TypeOfEvent> getListTypeObjectProperty() {
		return this.listTypeObjectProperty;
	}

	/**
	 * Gets the event list property.
	 *
	 * @return the event list property
	 */
	public ListProperty<Event> getEventListProperty() {
		return this.eventListProperty;
	}

	
	/**
	 * Gets the event list empty.
	 *
	 * @return the event list empty
	 */
	public BooleanProperty getEventListEmpty() {
		return this.eventListEmpty;
	}


	/**
	 * Gets the selected event property.
	 *
	 * @return the selected event property
	 */
	public ObjectProperty<Event> getSelectedEventProperty() {
		return this.selectedEventProperty;
	}
	

}
