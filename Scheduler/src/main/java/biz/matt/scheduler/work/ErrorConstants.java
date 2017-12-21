package biz.matt.scheduler.work;

public class ErrorConstants
{

	private ErrorConstants()
	{
	}

	public final static int TERMINAL_JOB_EXCEPTION_ID = 100;
	public final static String TERMINAL_JOB_EXCEPTION_MESSAGE = "Terminal Job Exception";
	
	public final static int JOB_EXECUTION_EXCEPTION_ID = 200;
	public final static String JOB_EXECUTION_EXCEPTION_MESSAGE = "General Job Execution Exception";
	
	public final static int UNKNOWN_EXCEPTION_ID = 300;
	public final static String UNKNOWN_EXCEPTION_MESSAGE = "Unknown Job Exception";
	
	public final static ErrorSummary DEFAULT_TERMINAL_JOB_EXCEPTION = new ErrorSummary(TERMINAL_JOB_EXCEPTION_ID, TERMINAL_JOB_EXCEPTION_MESSAGE);
	public final static ErrorSummary DEFAULT_JOB_EXECUTION_EXCEPTION = new ErrorSummary(JOB_EXECUTION_EXCEPTION_ID, JOB_EXECUTION_EXCEPTION_MESSAGE);
	public final static ErrorSummary UNKNOWN_JOB_ERROR_SUMMARY= new ErrorSummary(UNKNOWN_EXCEPTION_ID, UNKNOWN_EXCEPTION_MESSAGE);
}
