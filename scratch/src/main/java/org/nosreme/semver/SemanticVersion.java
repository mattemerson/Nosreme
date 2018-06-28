package org.nosreme.semver;

public class SemanticVersion implements Version 
{
	private VersionNumber versionNumber;
	private PreReleaseInfo preReleaseInfo;
	private BuildInfo buildInfo;
	
	@Override
	public VersionNumber getVersionNumber()
	{
		return this.versionNumber;
	}

	@Override
	public PreReleaseInfo getPreReleaseInfo()
	{
		return this.preReleaseInfo;
	}

	@Override
	public BuildInfo getBuildInfo()
	{
		return this.buildInfo;
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(getVersionNumber());				
		if (getPreReleaseInfo() != null)
		{
			builder.append("-");
			builder.append(getPreReleaseInfo());
		}
		if (getBuildInfo() != null)
		{
			builder.append("+");
			builder.append(getBuildInfo());
		}
		
		return builder.toString();
	}
}