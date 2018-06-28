package com.nosreme.svc.zcommon.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimeZone;

public class Event
{
	private int id;
	private LocalDate localDate;
	private LocalTime localTime;
	private TimeZone timezone; // this allows you to get to localized event
}
