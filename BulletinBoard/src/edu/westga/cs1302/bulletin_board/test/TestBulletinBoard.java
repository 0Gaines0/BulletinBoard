package edu.westga.cs1302.bulletin_board.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.EarliestFirstEventComparator;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Event.TypeOfEvent;
import edu.westga.cs1302.bulletin_board.model.LastestFirstEventComparator;

public class TestBulletinBoard {

	// Testing add method
	@Test
	public void testIfAddedEventTitleAlreadyExist() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("King Lear", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));

		board.addNewEvent(event1);

		assertThrows(IllegalArgumentException.class, () -> {
			board.addNewEvent(event2);
		});
	}

	@Test
	public void testEventBeingAddedHasDateBeforeCurrent() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2020, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));

		assertThrows(IllegalArgumentException.class, () -> {
			board.addNewEvent(event1);
		});
	}

	@Test
	public void testAddingOneEventToMap() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));

		board.addNewEvent(event1);

		assertFalse(board.getEventMap().isEmpty());
		assertTrue(board.getEventMap().containsKey("King Lear"));
		assertTrue(board.getEventMap().containsValue(event1));
	}

	@Test
	public void testAddingMultipleEventsToMap() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		board.addNewEvent(event1);
		board.addNewEvent(event2);

		assertFalse(board.getEventMap().isEmpty());
		assertTrue(board.getEventMap().containsKey("King Lear"));
		assertTrue(board.getEventMap().containsKey("Terrifer 2"));
		assertTrue(board.getEventMap().containsValue(event1));
		assertTrue(board.getEventMap().containsValue(event2));

	}

	// Testing remove method
	@Test
	public void testRemoveFromEmptyMap() {
		BulletinBoard board = new BulletinBoard();

		assertThrows(IllegalArgumentException.class, () -> {
			board.removeSpecificEvent("King Lear");
		});
	}

	@Test
	public void testRemoveFromSingleEventMap() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));

		board.addNewEvent(event1);
		board.addNewEvent(event2);

		board.removeSpecificEvent("King Lear");

		assertFalse(board.getEventMap().containsKey("King Lear"));
		assertFalse(board.getEventMap().containsValue(event1));
		assertTrue(board.getEventMap().containsKey("Terrifer 2"));
		assertTrue(board.getEventMap().containsValue(event2));

	}

	@Test
	public void testRemovingMultipleEventsFromMap() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		board.removeSpecificEvent("King Lear");
		board.removeSpecificEvent("Terrifer 2");
		
		assertFalse(board.getEventMap().containsKey("King Lear"));
		assertFalse(board.getEventMap().containsValue(event1));
		assertFalse(board.getEventMap().containsKey("Terrifer 2"));
		assertFalse(board.getEventMap().containsValue(event2));
		
		assertTrue(board.getEventMap().containsKey("President Biden Speech"));
		assertTrue(board.getEventMap().containsValue(event3));
	
	}
	
	@Test
	public void testEventMapIsEmptyGettingSortedEventList() {
		BulletinBoard board = new BulletinBoard();
		
		assertThrows(IllegalArgumentException.class, () -> {
			board.getSortedEventOfList(null, null);
		});
	}
	
	@Test
	public void testIfBothComparatorAndFilterEqualNull() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		List<Event> eventList = board.getSortedEventOfList(null, null);
		
		assertEquals(3, eventList.size());
		assertEquals("[Event [title=Terrifer 2], Event [title=President Biden Speech], Event [title=King Lear]]",eventList.toString());
		assertEquals("Terrifer 2",eventList.get(0).getTitle());
		assertEquals("President Biden Speech",eventList.get(1).getTitle());
		assertEquals("King Lear",eventList.get(2).getTitle());
		
	}
	
	@Test
	public void testIfComparatorNullAndFilterHasType() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2025, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		List<Event> eventList = board.getSortedEventOfList(null, TypeOfEvent.Theatrical);
		
		assertEquals(1,eventList.size());
		assertEquals("[Event [title=Terrifer 2]]", eventList.toString());
		assertEquals("Terrifer 2", eventList.get(0).getTitle());
	}
	
	@Test
	public void testIfComparatorNotNullAndFilterNullAndEarliestFirst() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2024, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		
		List<Event> eventList = board.getSortedEventOfList(new EarliestFirstEventComparator(), null);
		
		assertEquals("[Event [title=President Biden Speech], Event [title=Terrifer 2], Event [title=King Lear]]", eventList.toString());
		assertEquals("President Biden Speech", eventList.get(0).getTitle());
		assertEquals("Terrifer 2", eventList.get(1).getTitle());
		assertEquals("King Lear", eventList.get(2).getTitle());
		
	}
	
	@Test
	public void testIfComparatorNotNullAndFilterNullAndLatestFirst() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2024, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		
		List<Event> eventList = board.getSortedEventOfList(new LastestFirstEventComparator(), null);
		
		assertEquals("[Event [title=King Lear], Event [title=Terrifer 2], Event [title=President Biden Speech]]", eventList.toString());
		assertEquals("President Biden Speech", eventList.get(2).getTitle());
		assertEquals("Terrifer 2", eventList.get(1).getTitle());
		assertEquals("King Lear", eventList.get(0).getTitle());
		
	}
	
	@Test
	public void testIfComparatorNotNullAndFilterIsTheatricalAndEarliestFirst() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2024, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		List<Event> eventList = board.getSortedEventOfList(new EarliestFirstEventComparator(), TypeOfEvent.Theatrical);
		
		assertEquals("[Event [title=Terrifer 2], Event [title=King Lear]]", eventList.toString());
		assertEquals("Terrifer 2", eventList.get(0).getTitle());
		assertEquals("King Lear", eventList.get(1).getTitle());
		
		
	}
	
	@Test
	public void testIfComparatorNotNullAndFilterIsTheatricalAndLatestFirst() {
		BulletinBoard board = new BulletinBoard();
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2025, 11, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event2 = new Event("Terrifer 2", "Horror movie released in 2022", LocalDate.of(2024, 8, 15),
				Enum.valueOf(Event.TypeOfEvent.class, "Theatrical"));
		Event event3 = new Event("President Biden Speech", "President Biden speech held at the white house next year",
				LocalDate.of(2023, 1, 8), Enum.valueOf(Event.TypeOfEvent.class, "Political"));
		
		board.addNewEvent(event1);
		board.addNewEvent(event2);
		board.addNewEvent(event3);
		
		List<Event> eventList = board.getSortedEventOfList(new LastestFirstEventComparator(), TypeOfEvent.Theatrical);
		
		assertEquals("[Event [title=King Lear], Event [title=Terrifer 2]]", eventList.toString());
		assertEquals("Terrifer 2", eventList.get(1).getTitle());
		assertEquals("King Lear", eventList.get(0).getTitle());
		
		
	}

}
