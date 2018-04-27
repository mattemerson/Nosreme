package com.nosreme.svc.zcommon.event;

import java.time.LocalDateTime;

public class EventInstance
{
	// This ties back to the specific iteration of the Event
	private LocalDateTime scheduledTime;
	
	// This is associated with this specific instance
	private LocalDateTime actualStartTime;
	private LocalDateTime actualEndTime;
	private String state;

	// This is payload; in general different Events will have diferent payloads
	private String data;
}
