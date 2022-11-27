package edu.westga.cs1302.bulletin_board.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.Test;

import edu.westga.cs1302.bulletin_board.model.Event;

public class TestEvent {
	
	@Test
	public void testTitleIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Event(null, "a", LocalDate.of(2022, 11, 15), Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		});
	}
	
	@Test
	public void testTitleIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Event("", "a", LocalDate.of(2022, 11, 15), Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		});
	}
	
	@Test
	public void testTypeOfEventIsNotDefinedType() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Event("Show", "King Lear", LocalDate.of(2022, 11, 15), Enum.valueOf(Event.TypeOfEvent.class, "Play"));
		});
	}
	
	@Test
	public void testDateIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Event("Show", "King Lear", null, Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		});
	}
	
	@Test
	public void testInstaniateValidConstructor() {
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2022, 11, 15),Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		
		assertEquals("King Lear", event1.getTitle());
		assertEquals("Play, written in the 1600s", event1.getDescription());
		assertEquals("2022-11-15", event1.getDate().toString());
		assertEquals("Musical", event1.getType().toString());
	}
	
	@Test
	public void testGetEventSummary() {
		Event event1 = new Event("King Lear", "Play, written in the 1600s", LocalDate.of(2022, 11, 15),Enum.valueOf(Event.TypeOfEvent.class, "Musical"));
		
		assertEquals("King Lear : Play, written in the 1600s : 2022-11-15 : Musical", event1.getEventSummary());
	}
}
