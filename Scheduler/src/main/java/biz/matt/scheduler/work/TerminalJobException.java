package biz.matt.scheduler.work;

/**
 * If a task throws this <code>Exception</code>, it will not recover
 * 
 * @author memerson
 *
 */
public class TerminalJobException extends JobExecutionException
{
	public TerminalJobException()
	{
	}

	public TerminalJobException(String message)
	{
		super(message);
	}

	public TerminalJobException(Throwable cause)
	{
		super(cause);
	}

	public TerminalJobException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TerminalJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Guaranteed to return an <code>ErrorSummary</code>
	 * @return
	 */
	public ErrorSummary getErrorSummaryRequired()
	{
		ErrorSummary errorSummary = getErrorSummaryWithDefault(ErrorConstants.DEFAULT_TERMINAL_JOB_EXCEPTION);
		
		return errorSummary;
	}
}
