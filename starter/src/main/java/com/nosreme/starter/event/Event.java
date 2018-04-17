package com.nosreme.starter.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * A single occurence of an event
 * @author MEmerson
 *
 */
public class Event
{
	private EventType eventType;
	private CalendarType calendarType;
	private LocalDate localDate;
	private LocalTime localTime;
	private LocalDateTime localDateTime;

}
