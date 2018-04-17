package com.nosreme.starter.event;

import java.time.LocalDateTime;

public class Cadence
{
	private CadenceInterval cadenceInterval;
	private int interval;
	private LocalDateTime instant;
	
	public Cadence(int interval)
	{
		this.cadenceInterval = CadenceInterval.FIXED;
		this.interval = interval;
	}
	
	public Cadence(LocalDateTime localDateTime)
	{
		this.cadenceInterval = CadenceInterval.INSTANT;
		this.instant = localDateTime;
	}
	
	public int interval()
	{
		return this.interval;
	}
	
	public LocalDateTime instant()
	{
		return this.instant;
	}
}
