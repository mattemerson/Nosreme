/**
 * 
 */
package org.nosreme.scheduler.work;

/**
 * @author memerson
 *
 */
public class JobExecutionException extends RuntimeException
{
	private ErrorSummary myErrorSummary;
	
	/**
	 * 
	 */
	public JobExecutionException()
	{
	}

	/**
	 * @param message
	 */
	public JobExecutionException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public JobExecutionException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JobExecutionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JobExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Sets the <code>ErrorSummary</code> to use for reporting
	 * @param errorSummary
	 */
	public void setErrorSummary(ErrorSummary errorSummary)
	{
		myErrorSummary = errorSummary;
	}
	
	/**
	 * Gets the <code>ErrorSummary</code> to use for reporting
	 * @return errorSummary
	 */
	public ErrorSummary getErrorSummary()
	{
		return myErrorSummary;
	}
	
	/**
	 * Guaranteed to return an <code>ErrorSummary</code>
	 * @return
	 */
	public ErrorSummary getErrorSummaryRequired()
	{
		ErrorSummary errorSummary = getErrorSummaryWithDefault(ErrorConstants.DEFAULT_JOB_EXECUTION_EXCEPTION);
		
		return errorSummary;
	}
	
	/**
	 * Returns with a default if <code>ErrorSummary</code> is not set
	 * @param defaultErrorSummary
	 * @return
	 */
	protected ErrorSummary getErrorSummaryWithDefault(ErrorSummary defaultErrorSummary)
	{
		ErrorSummary errorSummary = getErrorSummary();
		return errorSummary = (errorSummary != null) ? errorSummary : defaultErrorSummary;		
	}
}
