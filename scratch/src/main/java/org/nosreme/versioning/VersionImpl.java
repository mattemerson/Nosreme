package org.nosreme.versioning;

public class VersionImpl implements Version
{
	private int myMajor;
	private int myMinor;
	private int myPatch;
	
	private VersionImpl(int major, int minor, int patch)
	{				
		myMajor = major;
		myMinor = minor;
		myPatch = patch;
	}
	
	public static Version newVersion(int major, int minor, int patch)
	{
		inputParameterCheck("major", major);
		inputParameterCheck("minor", minor);
		inputParameterCheck("patch", patch);
		
		return new VersionImpl(major, minor, patch);
	}
	
	private static void inputParameterCheck(String parameterName, int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("'" + parameterName + "' must be non-negative");
		}
	}
	
	@Override
	public int getMajor()
	{
		return myMajor;
	}

	@Override
	public int getMinor()
	{
		return myMinor;
	}

	@Override
	public int getPatch()
	{
		return myPatch;
	}
	
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(getMajor()).append(".")
		       .append(getMinor()).append(".")
		       .append(getPatch());
		return builder.toString();
	}
		
	@Override
	public Version incrementMajor()
	{
		int newMajor = getMajor() + 1;
		return new VersionImpl(newMajor, 0, 0);
	}
	
	@Override
	public Version incrementMinor()
	{
		int newMinor = getMinor() + 1;
		return new VersionImpl(getMajor(), newMinor, 0);		
	}
	
	@Override
	public Version incrementPatch()
	{
		int newPatch = getPatch() + 1;
		return new VersionImpl(getMajor(), getMinor(), newPatch);
	}	
}
