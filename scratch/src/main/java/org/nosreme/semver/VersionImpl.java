package org.nosreme.semver;

import org.nosreme.util.InputChecker;

public class VersionImpl implements Version
{
	private int major;
	private int minor;
	private int patch;
	private String suffix;
	private Version subversion;
	
	private VersionImpl(int major, int minor, int patch, String prefix, String suffix)
	{				
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.prefix = prefix;
		this.suffix = suffix;		
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
		
	@Override
	public int getMajor()
	{
		return major;
	}

	@Override
	public int getMinor()
	{
		return minor;
	}

	@Override
	public int getPatch()
	{
		return patch;
	}
	
	@Override
	public String getPrefix()
	{
		return this.prefix;
	}

	@Override
	public String getSuffix()
	{
		return this.suffix;
	}

	@Override
	public Version getSubversion()
	{
		return this.subversion;
	}
		
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		if (getPrefix() != null)
		{
			builder.append(getPrefix()).append("-");
		}
		
		builder.append(getMajor()).append(".")
		       .append(getMinor()).append(".")
		       .append(getPatch());
		return builder.toString();
		
		if (getSuffix() != null)
		{
			builder.append("-").append(getSuffix());
		}
		
		if (
	}	
}
