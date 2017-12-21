package biz.matt.scheduler.scheduler;

public class SchedulingFailedException extends RuntimeException {

	public SchedulingFailedException()
	{
	}

	public SchedulingFailedException(String message)
	{
		super(message);
	}

	public SchedulingFailedException(Throwable cause)
	{
		super(cause);
	}

	public SchedulingFailedException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SchedulingFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
