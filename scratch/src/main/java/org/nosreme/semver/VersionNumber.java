package org.nosreme.semver;

public class VersionNumber
{
	private int major;
	private int minor;
	private int patch;
	
	private VersionNumber(int major, int minor, int patch)
	{				
		this.major = major;
		this.minor = minor;
		this.patch = patch;	
	}
	
	/*
	public static Version newVersion(int major, int minor, int patch, String prefix, String suffix)
	{
		InputChecker.assertNonNegative("major", major);
		InputChecker.assertNonNegative("minor", minor);
		InputChecker.assertNonNegative("patch", patch);
		
		return new VersionImpl(major, minor, patch);
	}
	*/
		
	public int getMajor()
	{
		return major;
	}

	public int getMinor()
	{
		return minor;
	}

	public int getPatch()
	{
		return patch;
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
}
