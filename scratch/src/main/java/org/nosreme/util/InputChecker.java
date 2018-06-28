package org.nosreme.util;

public class InputChecker
{
	/**
	 * Helper static class
	 */
	private InputChecker()
	{		
	}
	
	public static void assertNonNull(String parameterName, Object value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("'" + parameterName + "' must be non-null");
		}
	}
	
	public static void assertNonNegative(String parameterName, int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("'" + parameterName + "' must be non-negative");
		}
	}
}
