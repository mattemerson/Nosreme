package org.nosreme.semver;

public class SemanticVersionImpl implements SemanticVersion 
{
	private Version version;
	private String suffix;
	private Version subversion;
	
	@Override
	public Version getVersion()
	{
		return version;
	}

	@Override
	public String getSuffix() 
	{
		return suffix;
	}

	@Override
	public Version getSubversion()
	{
		return subversion;
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(getVersion());
		if (getSuffix() != null)
		{
			builder.append("-")
		}
		
	}
}
