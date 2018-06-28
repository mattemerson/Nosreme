package org.nosreme.semver;

public interface Version
{
	VersionNumber getVersionNumber();
	PreReleaseInfo getPreReleaseInfo();
	BuildInfo getBuildInfo();
}
