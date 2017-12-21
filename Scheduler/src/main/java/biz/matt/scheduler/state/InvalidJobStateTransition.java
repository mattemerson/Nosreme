/**
 * 
 */
package biz.matt.scheduler.state;

/**
 * @author memerson
 *
 */
public class InvalidJobStateTransition extends RuntimeException
{
	/**
	 * 
	 */
	public InvalidJobStateTransition() {
	}

	/**
	 * @param message
	 */
	public InvalidJobStateTransition(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidJobStateTransition(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidJobStateTransition(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidJobStateTransition(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
